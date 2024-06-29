package com.august.ua.blackout.presentation.common

sealed interface NavigationEvent {

    data object None : NavigationEvent

    data object CloseScreen : NavigationEvent

    data class NavigateTo(val screenRoute: String) : NavigationEvent
}