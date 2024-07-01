package com.august.ua.blackout.presentation.onboarding.form

import com.august.ua.blackout.data.dto.Oblast

data class UserForm(
    var oblast: Oblast? = null,
    var queue: String? = null,
    var onboardingWasStartedBefore: Boolean = false,
    var isNotificationPermissionScreenSeen: Boolean = false,
) {
    fun isOblastSelected() = oblast != Oblast.Unknown || oblast != null
    fun isQueueSelected() =  queue != "-1" && queue != null
    fun isOblastAndQueueSelected() = isOblastSelected() && isQueueSelected()
}
