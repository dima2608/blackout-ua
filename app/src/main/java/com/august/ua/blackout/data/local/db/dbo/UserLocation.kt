package com.august.ua.blackout.data.local.db.dbo

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.august.ua.blackout.data.dto.OblastType

@Entity(tableName = "user_location")
data class UserLocation(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo("user_location_name")
    var locationName: String?,
    @ColumnInfo("user_location_icon")
    @DrawableRes
    var locationIcon: Int?,
    @ColumnInfo("user_location_color")
    val locationColor: OblastType?,
    @ColumnInfo("user_location")
    val selectedLocation: OblastType?,
    @ColumnInfo("user_queues")
    val selectedQueue: String?,
    @ColumnInfo("isPushEnabled")
    val isPushEnabled: Boolean?
)
