package com.august.ua.blackout.presentation.onboarding.form

import androidx.annotation.StringRes
import com.august.ua.blackout.data.dto.OblastType
import com.august.ua.blackout.data.dvo.OblastDvo
import com.august.ua.blackout.data.dvo.OblastsDvo

data class UserForm(
    var oblastType: OblastType? = null,
    var queue: String? = null,
    var onboardingWasStartedBefore: Boolean = false,
    var isNotificationPermissionScreenSeen: Boolean = false,
    @StringRes
    var oblastTypeError: Int? = null,
    @StringRes
    var queueError: Int? = null
) {
    fun isOblastSelected() = oblastType != OblastType.Unknown && oblastType != null
    fun isQueueSelected() =  queue != "-1" && queue != null
    fun isOblastAndQueueSelected() = isOblastSelected() && isQueueSelected()
}
