package com.august.ua.blackout.presentation.onboarding

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.august.ua.blackout.R
import com.august.ua.blackout.presentation.common.DevicePreviews
import com.august.ua.blackout.presentation.common.NavigationEvent
import com.august.ua.blackout.presentation.common.ScreenState
import com.august.ua.blackout.presentation.common.extensions.gradientBackground
import com.august.ua.blackout.presentation.onboarding.components.ImageAndDescription
import com.august.ua.blackout.presentation.onboarding.components.OnboardingBottomBar
import com.august.ua.blackout.presentation.onboarding.components.SelectOblastAndQueue
import com.august.ua.blackout.ui.components.SelectOblastBottomSheet
import com.august.ua.blackout.presentation.onboarding.event.OnboardingEvent
import com.august.ua.blackout.presentation.onboarding.state.OnboardingBottomSheetState
import com.august.ua.blackout.presentation.onboarding.state.OnboardingBottomSheetState.*
import com.august.ua.blackout.presentation.onboarding.state.OnboardingScreenState
import com.august.ua.blackout.ui.components.AppModalBottomSheet
import com.august.ua.blackout.ui.components.AppSnackBar
import com.august.ua.blackout.ui.components.OnboardingToolbar
import com.august.ua.blackout.ui.components.SelectQueueBottomSheet
import com.august.ua.blackout.ui.components.showSnackbar
import com.august.ua.blackout.ui.theme.BlackoutUaTheme
import com.august.ua.blackout.ui.theme.GradientAlternativePosition1
import com.august.ua.blackout.ui.theme.GradientAlternativePosition2
import com.august.ua.blackout.ui.theme.GradientAlternativePosition3
import com.august.ua.blackout.ui.theme.GradientAlternativePosition4
import com.august.ua.blackout.ui.theme.PeriwinkleGray
import com.august.ua.blackout.ui.theme.White
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    viewModel: OnboardingScreenViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val screenState by viewModel.screenState.collectAsState()
    val bottomSheetState by viewModel.bottomSheetState.collectAsState()
    val navEvent by viewModel.navEvent.collectAsState()

    val activity = (LocalContext.current as? Activity)

    OnboardingScreenContent(
        uiState = uiState,
        screenState = screenState,
        bottomSheetState = bottomSheetState,
        onEvent = viewModel::onEvent
    )

    BackHandler {
        viewModel.onEvent(OnboardingEvent.PreviousScreen)
    }

    LaunchedEffect(navEvent) {
        when(navEvent) {
            NavigationEvent.CloseScreen -> activity?.finish()
            is NavigationEvent.NavigateTo -> TODO()
            NavigationEvent.None -> Unit
        }
    }
    //activity?.finish() when close
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
private fun OnboardingScreenContent(
    uiState: OnboardingScreenState,
    screenState: ScreenState,
    bottomSheetState: OnboardingBottomSheetState,
    onEvent: (OnboardingEvent) -> Unit,
) {

    val localDensity = LocalDensity.current

    val sheetState = remember {
        SheetState(
            skipPartiallyExpanded = false,
            density = localDensity,
            initialValue = SheetValue.Hidden,
            skipHiddenState = false
        )
    }

    val snackbar = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

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
    ) { innerPadding ->

        Column(
            modifier = Modifier
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
                        SelectOblastAndQueue(
                            icon = uiState.image,
                            title = uiState.title,
                            description = uiState.description,
                            selectedOblast = uiState.oblastStr,
                            selectedQueue = uiState.queue,
                            oblastError = uiState.oblastError,
                            queueError = uiState.queueError,
                            onOblastClick = {
                                onEvent(OnboardingEvent.SelectOblast)
                            },
                            onQueueClick = {
                                onEvent(OnboardingEvent.SelectQueue)
                            }
                        )
                    }
                }
            }

            Box(modifier = Modifier.background(White)) {
                OnboardingBottomBar(
                    currentStep = uiState.currentIndicatorPosition,
                    totalSteps = uiState.indicatorSize
                ) {
                    onEvent(OnboardingEvent.NextScreen)
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

    if (bottomSheetState.showBottomSheet) {
        AppModalBottomSheet(
            onDismissRequest = {
                focusManager.clearFocus()
                onEvent(OnboardingEvent.ResetScreenState)
            },
            sheetState = sheetState,
            tonalElevation = 0.dp,
        ) {
            when (bottomSheetState) {
                Initial -> Unit
                is ShowOblastsBottomSheet -> {
                    SelectOblastBottomSheet(
                        oblasts = bottomSheetState.oblasts,
                        onClick = {
                            focusManager.clearFocus()
                            onEvent(OnboardingEvent.OblastChanged(it))
                        }
                    )
                }

                is ShowQueueBottomSheet -> {
                    SelectQueueBottomSheet(
                        oblast = bottomSheetState.oblast,
                        onClick = {
                            focusManager.clearFocus()
                            onEvent(OnboardingEvent.QueueChanged(it))
                        }
                    )
                }
            }
        }
    }

    if (bottomSheetState.showBottomSheet) {
        showBottomSheet(scope, sheetState)
    } else {
        hideBottomSheet(scope, sheetState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private fun showBottomSheet(scope: CoroutineScope, sheetState: SheetState) {
    scope.launch {
        sheetState.show()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private fun hideBottomSheet(scope: CoroutineScope, sheetState: SheetState) {
    scope.launch {
        sheetState.hide()
    }
}

@DevicePreviews
@Composable
private fun OnboardingScreenContentPreview() {
    BlackoutUaTheme {
        OnboardingScreenContent(
            uiState = OnboardingScreenState.HelloState,
            screenState = ScreenState.None,
            bottomSheetState = Initial,
            {}
        )
    }
}