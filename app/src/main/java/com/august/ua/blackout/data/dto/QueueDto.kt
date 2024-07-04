package com.august.ua.blackout.data.dto

import com.august.ua.blackout.domain.Entity
import com.google.gson.annotations.SerializedName


data class QueueDto(
    @SerializedName("queue")
    val queue: String?,
    @SerializedName("lightStatus")
    val lightStatus: Int?,
):Entity