package com.august.ua.blackout.data.local.db.dbo.with_embeded

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.august.ua.blackout.data.dto.OblastType
import com.august.ua.blackout.data.dto.OutrageStatus
import com.august.ua.blackout.data.dto.ShiftDto
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "outrage_table")
data class OutrageDbo(
    @PrimaryKey
    @ColumnInfo(name = "date")
    var date: String,
    @SerializedName("type")
    @ColumnInfo(name = "type")
    @Expose
    val type: OutrageStatus?,
    @SerializedName("region")
    @ColumnInfo(name = "region")
    @Expose
    val region: OblastType?,
    @SerializedName("changeCount")
    @ColumnInfo(name = "changeCount")
    @Expose
    val changeCount: Int?,
    @SerializedName("shifts")
    @Expose
    val shifts: List<OutrageShiftDbo>?
)