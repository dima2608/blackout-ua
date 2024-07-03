package com.august.ua.blackout.data.local.db.dbo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("available_cities")
data class AvailableCities(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @Embedded
    val availableCities: List<AvailableLocationDbo>,
)
