package com.august.ua.blackout.data.dto

import android.os.Parcelable
import com.august.ua.blackout.domain.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import retrofit2.http.Path

@Parcelize
data class OutrageRegionsDto(
    @SerializedName("region")
    val region: OblastType?,
    @SerializedName("queues")
    val queues: List<String>?,
): Entity, Parcelable
