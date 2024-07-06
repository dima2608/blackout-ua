package com.august.ua.blackout.data.local.db.dbo.with_embeded

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.august.ua.blackout.data.dto.QueueDto
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "outrage_shift_table")
data class OutrageShiftDbo(
    @PrimaryKey(autoGenerate = true)
    val outrageShiftId: Long = 0,
    @SerializedName("start")
    @ColumnInfo(name = "start")
    @Expose
    val start: String,
    @SerializedName("end")
    @ColumnInfo(name = "end")
    @Expose
    val end: String,
    @SerializedName("queues")
    @Expose
    val queues: List<OutrageQueueDbo>?
)