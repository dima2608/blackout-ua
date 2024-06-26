package com.august.ua.blackout.data.dto

import android.os.Parcelable
import com.august.ua.blackout.domain.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDto(
    val id: String?,
    val location: String?,
    val isPushEnabled: Boolean?,
) : Parcelable, Entity
