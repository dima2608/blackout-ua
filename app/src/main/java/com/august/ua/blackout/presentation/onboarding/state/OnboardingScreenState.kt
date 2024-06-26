package com.august.ua.blackout.presentation.onboarding.state

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.august.ua.blackout.R

sealed class OnboardingScreenState(
    @DrawableRes
    val image: Int,
    @StringRes
    val title: Int,
    @StringRes
    val description: Int,
    @StringRes
    val buttonTitle: Int = R.string.next,
    val isSkipPresent: Boolean = true,
    val indicatorSize: Int = 5,
    val currentIndicatorPosition: Int,
    val backButtonPresent: Boolean = true
) {
    data object HelloState: OnboardingScreenState(
        image = R.drawable.no_electro_logo,
        title = R.string.welcome,
        description = R.string.welcome_description,
        currentIndicatorPosition = 1,
        backButtonPresent = false,
    )
    data object SelectOblastState: OnboardingScreenState(
        image = R.drawable.no_electro_logo,
        title = R.string.choose_your_oblast,
        description = R.string.choose_your_oblast_description,
        currentIndicatorPosition = 2,
    )
    data object SelectQueueState: OnboardingScreenState(
        image = R.drawable.no_electro_logo,
        title = R.string.choose_your_queue,
        description = R.string.choose_your_queue_description,
        currentIndicatorPosition = 3,
    )
    data object GivePermissionState: OnboardingScreenState(
        image = R.drawable.no_electro_logo,
        title = R.string.give_notification_permission,
        description = R.string.give_notification_permission_description,
        currentIndicatorPosition = 4,
        buttonTitle = R.string.give_permission,
    )
    data object CompleteState: OnboardingScreenState(
        image = R.drawable.no_electro_logo,
        title = R.string.thanks,
        description = R.string.your_setup_is_successful,
        currentIndicatorPosition = 5,
        buttonTitle = R.string.lets_began,
    )
}