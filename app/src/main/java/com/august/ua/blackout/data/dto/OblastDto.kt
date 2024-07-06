package com.august.ua.blackout.data.dto

import android.os.Parcelable
import com.august.ua.blackout.data.local.db.dbo.AvailableLocationDbo
import com.august.ua.blackout.data.local.db.dbo.QueueDbo
import com.august.ua.blackout.domain.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
data class OblastDto(
    val oblastType: OblastType?,
    val queues: List<String>?
) : Entity, Parcelable

fun OblastDto.toAvailableLocationDbo() = AvailableLocationDbo(
    location = oblastType
)

fun OblastDto.toQueueDboList(availableLocationId: Long) = queues?.map {
    QueueDbo(
        parentAvailableLocationId = availableLocationId,
        queue = it,
    )
}.orEmpty()