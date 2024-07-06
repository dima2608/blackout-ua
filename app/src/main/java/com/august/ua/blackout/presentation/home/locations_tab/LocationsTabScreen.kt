package com.august.ua.blackout.presentation.home.locations_tab

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.august.ua.blackout.data.dvo.LocationsDvo
import com.august.ua.blackout.presentation.home.locations_tab.event.LocationsEvent
import com.august.ua.blackout.ui.common.extensions.itemBottomSpacer
import com.august.ua.blackout.ui.components.AppSnackBar
import com.august.ua.blackout.ui.components.LocationToolbar
import com.august.ua.blackout.ui.theme.Black
import com.august.ua.blackout.ui.theme.White
import com.august.ua.blackout.ui.theme.Yellow

@Composable
fun LocationsTabScreen(
    viewModel: LocationsTabViewModel = hiltViewModel(),

    ) {
    LocationsTabContent(
        locations = LocationsDvo(),
        onUiEvent = {}
    )
}

@Composable
private fun LocationsTabContent(
    locations: LocationsDvo,
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

            FloatingActionButton(
                onClick = {

                },
                contentColor = Black,
                containerColor = Yellow
            ) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(nestedScrollConnection),
            state = listState
        ) {

        }
    }
}