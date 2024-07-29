package com.august.ua.blackout.presentation.notification_permission.event

sealed class NotificationPermissionEvent {
    data object AskPermissionEvent: NotificationPermissionEvent()
    data object OnNavigateBackEvent: NotificationPermissionEvent()
    data class IsPermissionGranted(val isGranted: Boolean): NotificationPermissionEvent()
}
