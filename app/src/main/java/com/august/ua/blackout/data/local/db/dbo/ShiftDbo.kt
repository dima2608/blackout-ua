package com.august.ua.blackout.data.local.db.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("shifts_table")
data class ShiftDbo(
    @PrimaryKey(autoGenerate = true)
    val shiftId: Long = 0,
    @ColumnInfo("start")
    val start: String,
    @ColumnInfo("end")
    val end: String,
    @ColumnInfo("light_status")
    val lightStatus: Int,
    @ColumnInfo("parent_blackout_id")
    val parentBlackoutId: Long
)