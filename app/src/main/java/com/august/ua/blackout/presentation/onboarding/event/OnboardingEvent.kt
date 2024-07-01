package com.august.ua.blackout.presentation.onboarding.event

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
}