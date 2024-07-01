package com.august.ua.blackout.presentation.select_oblast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.august.ua.blackout.data.dto.Oblast
import com.august.ua.blackout.presentation.onboarding.OnboardingScreenViewModel
import com.august.ua.blackout.presentation.select_oblast.components.OblastItem
import com.august.ua.blackout.presentation.select_oblast.components.oblastItem
import com.august.ua.blackout.ui.components.AppModalBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectOblastBottomSheet(
    viewModel: SelectOblastViewModel = hiltViewModel(),
    onDismissRequest: () -> Unit
) {
    val screenState by viewModel.screenState.collectAsState()
    val dataState by viewModel.dataState.collectAsState()

    val scrollState = rememberLazyListState()

    AppModalBottomSheet(
        modifier = Modifier,
        onDismissRequest = onDismissRequest,
    ) {
        if (dataState?.oblasts.isNullOrEmpty()) {

        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                state = scrollState,
            ) {
                items(dataState?.oblasts!!) {
                    OblastItem(oblast = it) {

                    }
                }

            }
        }
    }
}