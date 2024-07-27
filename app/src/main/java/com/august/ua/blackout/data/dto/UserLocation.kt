package com.august.ua.blackout.data.dto

import android.os.Parcelable
import com.august.ua.blackout.domain.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserLocation(
    @SerializedName("name")
    val name: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("notificationLeadTime")
    val notificationLeadTime: Int,
    @SerializedName("queue")
    val queue: String
): Parcelable, Entity
