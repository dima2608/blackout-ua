package com.august.ua.blackout.presentation.home.locations_tab

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.august.ua.blackout.data.dvo.LocationDvo
import com.august.ua.blackout.presentation.common.NavigationEvent
import com.august.ua.blackout.presentation.home.locations_tab.components.ErrorMessage
import com.august.ua.blackout.presentation.home.locations_tab.components.LoadingNextPageItem
import com.august.ua.blackout.presentation.home.locations_tab.components.LocationExpandItem
import com.august.ua.blackout.presentation.home.locations_tab.components.PageLoader
import com.august.ua.blackout.presentation.home.locations_tab.event.LocationsEvent
import com.august.ua.blackout.ui.common.extensions.isScrollingUp
import com.august.ua.blackout.ui.common.extensions.itemBottomSpacer
import com.august.ua.blackout.ui.components.AppSnackBar
import com.august.ua.blackout.ui.components.LocationToolbar
import com.august.ua.blackout.ui.theme.Black
import com.august.ua.blackout.ui.theme.White
import com.august.ua.blackout.ui.theme.Yellow
import com.gigamole.composeshadowsplus.common.shadowsPlus

@Composable
fun LocationsTabScreen(
    viewModel: LocationsTabViewModel = hiltViewModel(),
    navigateTo: (String) -> Unit,
) {

    val navEvent by viewModel.navEvent.collectAsState()
    val locationsState = viewModel.locationsState.collectAsLazyPagingItems()

    LocationsTabContent(
        locationsPagingItem = locationsState,
        onUiEvent = viewModel::onUiEvent
    )

    LaunchedEffect(navEvent) {
        when (val event = navEvent) {
            NavigationEvent.CloseScreen -> Unit
            is NavigationEvent.NavigateTo -> navigateTo(event.screenRoute)
            NavigationEvent.None -> Unit
        }
        viewModel.onUiEvent(LocationsEvent.ResetNavState)
    }
}

@Composable
private fun LocationsTabContent(
    locationsPagingItem: LazyPagingItems<LocationDvo>,
    onUiEvent: (LocationsEvent) -> Unit
) {

    val listState = rememberLazyListState()
    var topAppBarBackgroundAlpha by rememberSaveable { mutableFloatStateOf(0f) }

    val scope = rememberCoroutineScope()
    val snackbar = remember {
        SnackbarHostState()
    }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                topAppBarBackgroundAlpha =
                    if (listState.firstVisibleItemIndex > 0) 1f else (listState.firstVisibleItemScrollOffset / 200f).coerceIn(
                        0f,
                        1f
                    )
                return Offset.Zero
            }
        }
    }

    Scaffold(
        topBar = {
            LocationToolbar(
                transparency = topAppBarBackgroundAlpha,
                showProgressIndicator = false
            )
        },
        snackbarHost = {
            AppSnackBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 12.dp),
                snackBarHostState = snackbar
            )
        },
        floatingActionButton = {

            AnimatedVisibility(visible = listState.isScrollingUp().value) {
                FloatingActionButton(
                    onClick = {
                        onUiEvent(LocationsEvent.AddNewLocation)
                    },
                    contentColor = White,
                    containerColor = Black
                ) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(nestedScrollConnection),
            state = listState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(innerPadding.calculateTopPadding()))
            }

            items(locationsPagingItem.itemCount) { index ->
                val location = locationsPagingItem[index]

                LocationExpandItem(
                    modifier = Modifier,
                    location = location ?: return@items,
                    onClick = {

                    },
                    onLongClick = {

                    }
                )
            }

            locationsPagingItem.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            PageLoader(modifier = Modifier.fillParentMaxSize())
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val error = locationsPagingItem.loadState.refresh as LoadState.Error
                        item {
                            ErrorMessage(
                                modifier = Modifier.fillParentMaxSize(),
                                message = error.error.localizedMessage!!,
                                onClickRetry = { retry() })
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            LoadingNextPageItem(modifier = Modifier)
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        val error = locationsPagingItem.loadState.append as LoadState.Error
                        item {
                            ErrorMessage(
                                modifier = Modifier,
                                message = error.error.localizedMessage!!,
                                onClickRetry = { retry() })
                        }
                    }
                }
            }

            itemBottomSpacer()
        }
    }
}