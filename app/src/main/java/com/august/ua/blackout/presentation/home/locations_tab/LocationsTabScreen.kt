package com.august.ua.blackout.presentation.home.locations_tab

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.august.ua.blackout.data.dvo.LocationsDvo
import com.august.ua.blackout.presentation.home.locations_tab.event.LocationsEvent
import com.august.ua.blackout.ui.common.extensions.itemBottomSpacer

@Composable
fun LocationsTabScreen(
    viewModel: LocationsTabViewModel = hiltViewModel(),

) {

}

@Composable
private fun LocationsTabContent(
    locations: LocationsDvo,
    onUiEvent: (LocationsEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {

        itemBottomSpacer()
    }
}