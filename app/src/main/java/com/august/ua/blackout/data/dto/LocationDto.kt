package com.august.ua.blackout.data.dto

import android.os.Parcelable
import com.august.ua.blackout.domain.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationDto(
    @SerializedName("name")
    val name: String?,
    @SerializedName("region")
    val region: OblastType?,
    @SerializedName("notificationLeadTime")
    val outragePushTime: OutragePushTime?,
    @SerializedName("queue")
    val queue: String?
): Entity, Parcelable