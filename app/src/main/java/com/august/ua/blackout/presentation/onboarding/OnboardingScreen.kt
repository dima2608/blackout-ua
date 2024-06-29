package com.august.ua.blackout.presentation.onboarding

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.august.ua.blackout.presentation.common.ScreenState
import com.august.ua.blackout.presentation.onboarding.state.OnboardingScreenState
import com.august.ua.blackout.ui.components.AppSnackBar

@Composable
fun OnboardingScreen(
    viewModel: OnboardingScreenViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    val navEvent by viewModel.navEvent.collectAsStateWithLifecycle()

    val activity = (LocalContext.current as? Activity)

    OnboardingScreenContent(
        uiState = uiState,
        screenState = screenState
    )
}

@Composable
private fun OnboardingScreenContent(
    uiState: OnboardingScreenState,
    screenState: ScreenState
) {

    val snackbar = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()


    Scaffold(
        snackbarHost = {
            AppSnackBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 28.dp),
                snackBarHostState = snackbar
            )
        }
    ) { innerPadding ->

    }
}