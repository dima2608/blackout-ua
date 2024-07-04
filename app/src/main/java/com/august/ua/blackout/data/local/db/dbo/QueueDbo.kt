package com.august.ua.blackout.data.local.db.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.august.ua.blackout.data.dto.OblastDto

@Entity("queue")
data class QueueDbo(
    @PrimaryKey(autoGenerate = true)
    val queueId: Long = 0,
    @ColumnInfo("parentAvailableLocationId")
    val parentAvailableLocationId: Long,
    @ColumnInfo("queue")
    val queue: String,
    @ColumnInfo("light_status")
    val lightStatus: String
)