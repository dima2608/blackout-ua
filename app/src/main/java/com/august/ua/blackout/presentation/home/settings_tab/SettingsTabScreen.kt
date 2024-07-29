package com.august.ua.blackout.presentation.home.settings_tab

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.august.ua.blackout.R
import com.august.ua.blackout.presentation.common.ScreenState
import com.august.ua.blackout.presentation.create_update_location.state.CreateUpdateLocationState
import com.august.ua.blackout.ui.components.AppSnackBar
import com.august.ua.blackout.ui.components.TitleToolbar

@Composable
fun SettingsTabScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val screenState by viewModel.screenState.collectAsState()
    val navEvent by viewModel.navEvent.collectAsState()

    SettingsTabContent(
        uiState = uiState,
        screenState = screenState,
        onActionPerformed = {}
    )
}

@Composable
private fun SettingsTabContent(
    uiState: CreateUpdateLocationState,
    screenState: ScreenState,
    onActionPerformed: ((() -> Unit) -> Unit)?,
) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val snackbar = remember {
        SnackbarHostState()
    }

    var link by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier,
        topBar = {
            TitleToolbar(
                title = stringResource(id = R.string.settings),
                showProgressIndicator = screenState == ScreenState.Loading
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
        }
    ) { innerPadding ->


    }
}
