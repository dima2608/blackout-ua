package com.august.ua.blackout.data.local.db.dbo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation


data class AvailableLocationsWithQueuesDbo(
    @Embedded
    val availableLocationDbo: AvailableLocationDbo,
    @Relation(
        parentColumn = "availableLocationId",
        entityColumn = "parentAvailableLocationId"
    )
    val queues: List<QueueDbo>
)
