package com.august.ua.blackout.data.dto

import android.os.Parcelable
import com.august.ua.blackout.domain.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OutrageDto(
    @SerializedName("type")
    val type: OutrageStatus?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("region")
    val region: OblastType?,
    @SerializedName("changeCount")
    val changeCount: Int?,
    @SerializedName("shifts")
    val shifts: List<ShiftDto>?,
): Entity, Parcelable