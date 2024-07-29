package com.august.ua.blackout.presentation.notification_permission.state

sealed class NotificationPermissionScreenState  {
    data object InitialState: NotificationPermissionScreenState()
    data object AskPermissionState: NotificationPermissionScreenState()

}