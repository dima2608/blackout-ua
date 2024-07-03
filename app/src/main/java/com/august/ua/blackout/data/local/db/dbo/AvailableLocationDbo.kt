package com.august.ua.blackout.data.local.db.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.august.ua.blackout.data.dto.OblastType

@Entity("available_location")
data class AvailableLocationDbo(
    @PrimaryKey(autoGenerate = true)
    var availableLocationId: Long = 0,
    @ColumnInfo("location")
    val location: OblastType?,
)
