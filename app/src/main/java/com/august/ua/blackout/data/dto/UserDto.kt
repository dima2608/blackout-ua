package com.august.ua.blackout.data.dto

import android.os.Parcelable
import com.august.ua.blackout.domain.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDto(
    val id: String?,
    val isPushEnabled: Boolean?,
    val oblast: Oblast?,
    val queues: Int?,
) : Parcelable, Entity