package com.august.ua.blackout.data.dto

import com.august.ua.blackout.data.local.db.dbo.AvailableLocationDbo
import com.august.ua.blackout.data.local.db.dbo.QueueDbo
import com.august.ua.blackout.domain.Entity

data class OblastDto(
    val oblastType: OblastType?,
    val queues: List<String>?
): Entity

fun OblastDto.toAvailableLocationDbo() = AvailableLocationDbo(
    location = oblastType
)

fun OblastDto.toQueueDboList(availableLocationId: Long) = queues?.map {
    QueueDbo(
        parentAvailableLocationId = availableLocationId,
        queue = it,
    )
}.orEmpty()