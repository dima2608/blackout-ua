package com.august.ua.blackout.presentation.onboarding

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.august.ua.blackout.R
import com.august.ua.blackout.presentation.common.DevicePreviews
import com.august.ua.blackout.presentation.common.ScreenState
import com.august.ua.blackout.presentation.common.extensions.blackoutRadialGradientBorder
import com.august.ua.blackout.presentation.common.extensions.gradientBackground
import com.august.ua.blackout.presentation.onboarding.components.CompletionStepsProgressButton
import com.august.ua.blackout.presentation.onboarding.components.ImageAndDescription
import com.august.ua.blackout.presentation.onboarding.components.OnboardingBottomBar
import com.august.ua.blackout.presentation.onboarding.event.OnboardingEvent
import com.august.ua.blackout.presentation.onboarding.state.OnboardingScreenState
import com.august.ua.blackout.ui.components.AppSnackBar
import com.august.ua.blackout.ui.components.OnboardingToolbar
import com.august.ua.blackout.ui.components.showSnackbar
import com.august.ua.blackout.ui.theme.BlackoutUaTheme
import com.august.ua.blackout.ui.theme.GradientAlternativePosition1
import com.august.ua.blackout.ui.theme.GradientAlternativePosition2
import com.august.ua.blackout.ui.theme.GradientAlternativePosition3
import com.august.ua.blackout.ui.theme.GradientAlternativePosition4
import com.august.ua.blackout.ui.theme.PeriwinkleGray

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
        screenState = screenState,
        onEvent = viewModel::onEvent
    )

    BackHandler {
        viewModel.onEvent(OnboardingEvent.PreviousScreen)
    }

    //activity?.finish() when close
}

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
private fun OnboardingScreenContent(
    uiState: OnboardingScreenState,
    screenState: ScreenState,
    onEvent: (OnboardingEvent)-> Unit,
) {

    val snackbar = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .gradientBackground(
                colors = listOf(
                    GradientAlternativePosition1,
                    GradientAlternativePosition2,
                    GradientAlternativePosition3,
                    GradientAlternativePosition4,
                    PeriwinkleGray,
                ),
                angle = 90f
            ),
        topBar = {
            OnboardingToolbar(
                isBackButtonVisible = uiState.backButtonPresent,
                showProgressIndicator = screenState == ScreenState.Loading,
                onClick = {
                    onEvent(OnboardingEvent.PreviousScreen)
                }
            )
        },
        snackbarHost = {
            AppSnackBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 28.dp),
                snackBarHostState = snackbar
            )
        },
        bottomBar = {
            OnboardingBottomBar(
                currentStep = uiState.currentIndicatorPosition,
                totalSteps = uiState.indicatorSize
            ) {
                onEvent(OnboardingEvent.NextScreen)
            }
        },
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 38.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                when (uiState) {
                    OnboardingScreenState.Initial -> Unit
                    OnboardingScreenState.HelloState -> {
                        ImageAndDescription(
                            image = uiState.image,
                            title = uiState.title,
                            description = uiState.description
                        )
                    }
                    OnboardingScreenState.GivePermissionState -> {
                        ImageAndDescription(
                            image = uiState.image,
                            title = uiState.title,
                            description = uiState.description
                        )
                    }
                    OnboardingScreenState.CompleteState -> {
                        ImageAndDescription(
                            image = uiState.image,
                            title = uiState.title,
                            description = uiState.description
                        )
                    }
                    is OnboardingScreenState.SelectOblastAndQueueState -> {
                        ImageAndDescription(
                            image = uiState.image,
                            title = uiState.title,
                            description = uiState.description
                        )
                    }
                }
            }
        }
    }

    val noInternetConnectionTitle = stringResource(id = R.string.no_internet_connection)
    val noInternetConnectionLabel = stringResource(id = R.string.retry)

    LaunchedEffect(screenState) {
        when (screenState) {
            is ScreenState.ErrorState -> showSnackbar(
                message = screenState.error,
                scope = scope,
                snackbar = snackbar,
                onSnackbarDismissed = {
                    onEvent(OnboardingEvent.OnSnackbarDismissed)
                }
            )
            ScreenState.Loading -> {}
            ScreenState.NoInternetConnection -> showSnackbar(
                message = noInternetConnectionTitle,
                actionLabel = noInternetConnectionLabel,
                scope = scope,
                snackbar = snackbar,
                onSnackbarDismissed = {
                    onEvent(OnboardingEvent.OnSnackbarDismissed)
                },
                onActionPerformed = {
                    onEvent(OnboardingEvent.OnSnackbarRetry)
                }
            )

            ScreenState.NoInternetConnectionFullscreen -> Unit
            ScreenState.None -> {
                snackbar.currentSnackbarData?.dismiss()
            }
        }
    }
}

@DevicePreviews
@Composable
private fun OnboardingScreenContentPreview() {
    BlackoutUaTheme {
        OnboardingScreenContent(
            uiState = OnboardingScreenState.HelloState,
            screenState = ScreenState.None,
            {}
        )
    }
}