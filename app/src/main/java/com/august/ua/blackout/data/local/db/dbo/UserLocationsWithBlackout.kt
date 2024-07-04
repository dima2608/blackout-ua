package com.august.ua.blackout.data.local.db.dbo

import androidx.room.Embedded
import androidx.room.Relation

data class UserLocationsWithBlackout(
    @Embedded
    val userLocation: UserLocationDbo,
    @Relation(
        parentColumn = "locationId",
        entityColumn = "userLocationId"
    )
    val blackout: BlackoutDbo
)