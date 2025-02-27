package com.august.ua.blackout.presentation.onboarding.state

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.august.ua.blackout.R
import com.august.ua.blackout.data.dto.OblastType

sealed class OnboardingScreenState(
    @DrawableRes
    val image: Int,
    @StringRes
    val title: Int,
    @StringRes
    val description: Int,
    @StringRes
    val buttonTitle: Int = R.string.next,
    val indicatorSize: Int = 4,
    val currentIndicatorPosition: Int,
    val backButtonPresent: Boolean = true
) {

    data object Initial : OnboardingScreenState(
        currentIndicatorPosition = 0,
        title = R.string.app_name,
        image = R.drawable.saly_10_dummy,
        description = R.string.welcome_description
    )

    data object HelloState: OnboardingScreenState(
        image = R.drawable.saly_10_dummy,
        title = R.string.welcome,
        description = R.string.welcome_description,
        currentIndicatorPosition = 1,
        backButtonPresent = false,
    )
    data class SelectOblastAndQueueState(
        val oblastType: OblastType,
        val queue: String,
        val oblastStr: String,
        @StringRes
        val oblastError: Int?,
        @StringRes
        val queueError: Int?,
    ): OnboardingScreenState(
        image = R.drawable.saly_10_dummy,
        title = R.string.choose_your_oblast_title,
        description = R.string.choose_your_queue_and_region_description,
        currentIndicatorPosition = 2,
    )

    data object GivePermissionState: OnboardingScreenState(
        image = R.drawable.saly_10_dummy,
        title = R.string.give_notification_permission,
        description = R.string.give_notification_permission_description,
        currentIndicatorPosition = 3,
        buttonTitle = R.string.give_permission,
    )
    data object CompleteState: OnboardingScreenState(
        image = R.drawable.saly_10_dummy,
        title = R.string.thanks,
        description = R.string.your_setup_is_successful,
        currentIndicatorPosition = 4,
        buttonTitle = R.string.lets_began,
    )
}