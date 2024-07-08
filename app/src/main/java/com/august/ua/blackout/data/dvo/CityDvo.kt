package com.august.ua.blackout.data.dvo

import com.august.ua.blackout.data.dto.OblastType
import com.google.gson.annotations.SerializedName

data class CityDvo(
    val id: Long,
    val oblastType: OblastType,
    val queues: List<QueueDvo>,
    var isSelected: Boolean,
    val link: String? = null,
    var lastUpdate: Long = System.currentTimeMillis()
)