package com.august.ua.blackout.data.local.db.dbo.with_embeded

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "outrage_queue_table")
data class OutrageQueueDbo(
    @PrimaryKey
    @ColumnInfo(name = "queue")
    var queue: String,
    @ColumnInfo(name = "lightStatus")
    var lightStatus: Int?
)