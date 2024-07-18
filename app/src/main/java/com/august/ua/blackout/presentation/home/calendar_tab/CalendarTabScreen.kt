package com.august.ua.blackout.presentation.home.calendar_tab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.august.ua.blackout.presentation.home.calendar_tab.event.Schedule
import com.august.ua.blackout.presentation.home.calendar_tab.state.CalendarTabUIState
import com.august.ua.blackout.ui.components.CalendarToolbar

@Composable
fun CalendarTabScreen(
    viewModel: CalendarTabViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    CalendarTabContent(
        uiState = uiState
    )
}

@Composable
private fun CalendarTabContent(
    uiState: CalendarTabUIState,
) {
    Scaffold(
        topBar = {
            CalendarToolbar(
                onMenu = {

                },
            )
        },
        //containerColor = White
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(innerPadding.calculateTopPadding())
            )

            Schedule(
                modifier = Modifier,
                uiState = uiState,
            )
        }

    }
}