package com.august.ua.blackout.presentation.common

sealed class ScreenState {
    data object None : ScreenState()
    data object Loading : ScreenState()
    data class ErrorState(val error: String, val customRetryTitle: String? = null) : ScreenState()
    data object NoInternetConnection : ScreenState()
    data object NoInternetConnectionFullscreen : ScreenState()
}