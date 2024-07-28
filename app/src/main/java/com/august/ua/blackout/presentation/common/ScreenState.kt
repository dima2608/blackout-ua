package com.august.ua.blackout.presentation.common

sealed class ScreenState {
    data object None : ScreenState()
    data object Loading : ScreenState()
    data class ErrorState(val error: String, val customRetryTitle: String? = null) : ScreenState()
    class NoInternetConnection(
        val action: () -> Unit
    ) : ScreenState()
    data object NoInternetConnectionFullscreen : ScreenState()
}