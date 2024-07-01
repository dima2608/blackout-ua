package com.august.ua.blackout.presentation.onboarding

enum class OnboardingPage(
    itOrdinal: Int
) {
    Undefined(itOrdinal = -1),
    Hello(itOrdinal = 0),
    SelectOblastAndQueue(itOrdinal = 1),
    GiveNotificationPermission(itOrdinal = 2),
    Enjoy(itOrdinal = 3)
}