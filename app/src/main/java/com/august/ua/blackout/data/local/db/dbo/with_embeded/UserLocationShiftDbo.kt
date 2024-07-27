package com.august.ua.blackout.data.local.db.dbo.with_embeded

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_location_shift_table")
data class UserLocationShiftDbo(
    @PrimaryKey(autoGenerate = true)
    val outrageShiftId: Long = 0,
//    @ColumnInfo("shift_date")
//    val shiftDate: String,
    @SerializedName("start")
    @ColumnInfo(name = "start")
    val start: String,
    @SerializedName("end")
    @ColumnInfo(name = "end")
    val end: String,
    @SerializedName("lightStatus")
    @ColumnInfo(name = "lightStatus")
    val lightStatus: Int,
)