package com.august.ua.blackout.data.local.db.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("blackout")
data class BlackoutDbo(
    @PrimaryKey(autoGenerate = true)
    val blackoutId: Long = 0,
    @ColumnInfo("last_update")
    val lastUpdate: String,
    @ColumnInfo("date")
    val date: String,
    @ColumnInfo("user_location_id")
    val userLocationId: Long,
    @ColumnInfo("change_count")
    val changeCount: Int,
)