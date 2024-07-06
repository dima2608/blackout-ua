package com.august.ua.blackout.data.local.db.dbo.with_embeded

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomWarnings
import androidx.room.TypeConverters
import com.august.ua.blackout.data.dto.OutrageDto
import com.august.ua.blackout.data.local.db.dbo.converters.Converters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "outrage_full_table")
@SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
data class OutrageFullDbo(
    @PrimaryKey
    val outrageId: Long = 0,
    @SerializedName("lastUpdate")
    @Expose
    val lastUpdate: String?,
    @SerializedName("accessDate")
    @Expose
    val accessDate: String?,
    @SerializedName("outrages")
    @Expose
    val outrages: List<OutrageDbo>?
)