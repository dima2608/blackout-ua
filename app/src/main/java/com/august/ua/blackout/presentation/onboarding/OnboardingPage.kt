package com.august.ua.blackout.presentation.onboarding

enum class OnboardingPage(
    itOrdinal: Int
) {
    Undefined(itOrdinal = -1),
    Hello(itOrdinal = 0),
    SelectOblast(itOrdinal = 1),
    SelectQueue(itOrdinal = 2),
    GiveNotificationPermission(itOrdinal = 3),
    Enjoy(itOrdinal = 4)
}