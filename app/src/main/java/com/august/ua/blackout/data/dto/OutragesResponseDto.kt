package com.august.ua.blackout.data.dto

import android.os.Parcelable
import com.august.ua.blackout.domain.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OutragesResponseDto(
    @SerializedName("lastUpdate")
    val lastUpdate: String,
    @SerializedName("accessDate")
    val accessDate: String,
    @SerializedName("outrages")
    val outrages: List<OutrageDto>?
): Entity, Parcelable