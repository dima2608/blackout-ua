package com.august.ua.blackout.presentation.onboarding.state

import androidx.annotation.StringRes
import com.august.ua.blackout.R
import com.august.ua.blackout.data.dvo.OblastDvo
import com.august.ua.blackout.data.dvo.OblastsDvo
import com.august.ua.blackout.data.dvo.QueuesDvo

sealed class OnboardingBottomSheetState(
    @StringRes
    val title: Int? = null,
    val showBottomSheet: Boolean = false
) {
    data object Initial: OnboardingBottomSheetState()

    data class ShowOblastsBottomSheet(
        val oblasts: OblastsDvo
    ): OnboardingBottomSheetState(
        title = R.string.choose_your_oblast,
        showBottomSheet = true
    )

    data class ShowQueueBottomSheet(
        val oblast: OblastDvo
    ): OnboardingBottomSheetState(
        title = R.string.choose_your_queue,
        showBottomSheet = true
    )
}