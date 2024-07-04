package com.august.ua.blackout.data.local.db.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.august.ua.blackout.data.dto.OblastType
import com.august.ua.blackout.data.dto.UserDto

@Entity(tableName = "user")
data class UserDbo(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @ColumnInfo("isBlackoutScheduleChangesPushEnabled")
    val isBlackoutScheduleChangesPushEnabled: Boolean = false,

    @ColumnInfo("onboardingWasViewed")
    val onboardingWasViewed: Boolean = false,
)

fun UserDbo.toUserDto() = UserDto(
    id = id.toString(),
    isPushEnabled = false,
    oblastType = OblastType.Kyiv,
    queue = "1",
    isNotificationPermissionScreenSeen = false,
    isOnboardingComplete = false
)
