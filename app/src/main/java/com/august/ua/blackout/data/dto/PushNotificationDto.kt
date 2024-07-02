package com.august.ua.blackout.data.dto

import android.os.Parcelable
import com.august.ua.blackout.domain.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
data class PushNotificationDto(
    val pushId: String,
    val title: String,
    val body: String,
    val date: String?,
    val entityId: String?,
): Entity, Parcelable