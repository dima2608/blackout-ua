package com.august.ua.blackout.presentation.notification_permission

import android.Manifest
import android.os.Build
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.august.ua.blackout.R
import com.august.ua.blackout.presentation.common.DevicePreviews
import com.august.ua.blackout.presentation.common.NavigationEvent
import com.august.ua.blackout.presentation.notification_permission.event.NotificationPermissionEvent
import com.august.ua.blackout.presentation.notification_permission.state.NotificationPermissionScreenState
import com.august.ua.blackout.ui.components.PrimaryButton
import com.august.ua.blackout.ui.components.SecondaryContainerButton
import com.august.ua.blackout.ui.theme.BlackoutUaTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@Composable
fun GiveNotificationPermissionScreen(
    viewModel: GiveNotificationPermissionViewModel = hiltViewModel(),
    close: () -> Unit
) {
    val screenState by viewModel.screenState.collectAsState()
    val navigationState by viewModel.navigationState.collectAsState()

    LaunchedEffect(navigationState) {
        when (navigationState) {
            NavigationEvent.CloseScreen -> close()
            is NavigationEvent.NavigateTo -> Unit
            NavigationEvent.None -> Unit
        }
        viewModel.resetNavigateBackState()
    }

    GiveNotificationPermissionScreenContent(
        screenState = screenState,
        onRejectClick = {
            viewModel.onEvent(NotificationPermissionEvent.OnNavigateBackEvent)
        },
        onTurnOnClick = {
            viewModel.onEvent(NotificationPermissionEvent.AskPermissionEvent)
        },
        onPermissionResult = { isGranted ->
            viewModel.onEvent(NotificationPermissionEvent.OnNavigateBackEvent)
            viewModel.resetUiState()
        }
    )
}

@Composable
private fun GiveNotificationPermissionScreenContent(
    screenState: NotificationPermissionScreenState,
    onTurnOnClick: () -> Unit,
    onRejectClick: () -> Unit,
    onPermissionResult: (isGranted: Boolean) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValues ->

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Image(
                    modifier = Modifier
                        .padding(top = paddingValues.calculateTopPadding())
                        .padding(top = 76.dp)
                        .padding(vertical = 12.dp),
                    painter = painterResource(id = R.drawable.notification_permission),
                    contentDescription = null
                )

                Text(
                    text = stringResource(id = R.string.enable_notifications),
                    modifier = Modifier.padding(top = 12.dp),
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = stringResource(id = R.string.enable_notifications_description),
                    modifier = Modifier
                        .padding(horizontal = 32.dp, vertical = 12.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )

            }

            ConfirmationButtons(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = paddingValues.calculateBottomPadding())
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .align(Alignment.BottomCenter),
                onRejectClick = onRejectClick,
                onTurnOnClick = onTurnOnClick
            )
        }

        when (screenState) {
            NotificationPermissionScreenState.AskPermissionState -> {
                AskNotificationPermission { isPermissionGranted ->
                    onPermissionResult(isPermissionGranted)
                }
            }
            NotificationPermissionScreenState.InitialState -> Unit
        }
    }
}

@Composable
fun ConfirmationButtons(
    modifier: Modifier,
    onRejectClick: () -> Unit,
    onTurnOnClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SecondaryContainerButton(
            modifier = Modifier
                .weight(1f)
                .padding(end = 6.dp),
            text = stringResource(id = R.string.reject),
            onClick = {
                onRejectClick()
            }
        )

        PrimaryButton(
            modifier = Modifier
                .weight(1f)
                .padding(start = 6.dp),
            text = stringResource(id = R.string.enable),
            onClick = {
                onTurnOnClick()
            }
        )
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun AskNotificationPermission(
    onPermissionResult: (isGranted: Boolean) -> Unit,
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

        val notificationPermission = rememberPermissionState(
            permission = Manifest.permission.POST_NOTIFICATIONS,
            onPermissionResult = {
                onPermissionResult(it)
            }
        )

        if (!notificationPermission.status.isGranted) {
            LaunchedEffect(Unit) {
                notificationPermission.launchPermissionRequest()
            }
        } else {
            onPermissionResult(true)
        }
    }
}

@DevicePreviews
@Composable
private fun NotificationPermissionScreenContentPreview() {
    BlackoutUaTheme {
        GiveNotificationPermissionScreenContent(
            onTurnOnClick = {},
            onRejectClick = {},
            onPermissionResult = {},
            screenState = NotificationPermissionScreenState.InitialState
        )
    }
}