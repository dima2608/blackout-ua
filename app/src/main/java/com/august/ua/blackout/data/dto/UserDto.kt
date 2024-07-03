package com.august.ua.blackout.data.dto

import android.os.Parcelable
import com.august.ua.blackout.data.local.db.dbo.UserDbo
import com.august.ua.blackout.domain.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDto(
    val id: String? = null,
    val isPushEnabled: Boolean? = false,
    val oblastType: OblastType? = null,
    val queue: String? = null,
    val isNotificationPermissionScreenSeen: Boolean? = false,
    val isOnboardingComplete: Boolean? = false
) : Parcelable, Entity

fun UserDto.toUserDbo() = UserDbo(
    id = 1,
    isPushEnabled = isPushEnabled ?: false,
    isNotificationPermissionScreenSeen = isNotificationPermissionScreenSeen ?: false,

)
