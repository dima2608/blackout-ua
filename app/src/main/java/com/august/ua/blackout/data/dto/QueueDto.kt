package com.august.ua.blackout.data.dto

import android.os.Parcelable
import com.august.ua.blackout.domain.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class QueueDto(
    @SerializedName("queue")
    val queue: String?,
    @SerializedName("lightStatus")
    val lightStatus: Int?,
) : Entity, Parcelable