package com.august.ua.blackout.presentation.home.calendar_tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.august.ua.blackout.presentation.home.calendar_tab.components.CalendarDrawer
import com.august.ua.blackout.presentation.home.calendar_tab.components.Schedule
import com.august.ua.blackout.presentation.home.calendar_tab.state.CalendarTabUIState
import com.august.ua.blackout.ui.components.CalendarToolbar
import com.august.ua.blackout.ui.theme.EggshellAlpha80
import kotlinx.coroutines.launch

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
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            CalendarDrawer()
        },
    ) {
        Scaffold(
            topBar = {
                CalendarToolbar(
                    onMenu = {
                        scope.launch {
                            drawerState.open()
                        }
                    },
                )
            },
            //containerColor = White
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
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
}