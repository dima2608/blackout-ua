package com.august.ua.blackout.presentation.onboarding.event

import com.august.ua.blackout.data.dvo.OblastDvo

sealed interface OnboardingEvent {
    data object NextScreen: OnboardingEvent
    data object PreviousScreen: OnboardingEvent
    data object SelectOblast: OnboardingEvent
    data object SelectQueue: OnboardingEvent
    data object GivePermission: OnboardingEvent
    data object FinishOnboarding: OnboardingEvent
    data object ResetScreenState: OnboardingEvent
    data object OnSnackbarDismissed: OnboardingEvent
    data object OnSnackbarRetry: OnboardingEvent

    data class OblastChanged(val oblast: OblastDvo): OnboardingEvent
    data class QueueChanged(val queue: String): OnboardingEvent
}