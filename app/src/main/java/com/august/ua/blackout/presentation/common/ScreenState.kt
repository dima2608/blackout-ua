package com.august.ua.blackout.presentation.common

sealed class ScreenState {
    data object None : ScreenState()
    data object Loading : ScreenState()
    data class ErrorState(val error: String) : ScreenState()
    data object NoInternetConnection : ScreenState()
    data object NoInternetConnectionFullscreen : ScreenState()
}