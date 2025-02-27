package com.august.ua.blackout.data.local.db.dbo

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.august.ua.blackout.data.dto.OblastType
import com.august.ua.blackout.data.dvo.LocationColorType
import com.august.ua.blackout.data.dvo.LocationIconType

@Entity(tableName = "user_location")
data class UserLocationDbo(
    @PrimaryKey(autoGenerate = true)
    var locationId: Long = 0,
    @ColumnInfo("location_order")
    var locationOrder: Int,
    @ColumnInfo("user_location_name")
    var locationName: String,
    @ColumnInfo("selected_location_icon_id")
    var locationIconType: LocationIconType,
    @ColumnInfo("selected_location_color_id")
    val locationColorType: LocationColorType,
    @ColumnInfo("selected_location")
    val selectedLocation: OblastType,
    @ColumnInfo("selected_queue")
    val selectedQueue: String,
    @ColumnInfo("is_location_push_enabled")
    val isLocationPushEnabled: Boolean,

    @ColumnInfo("blackout_id")
    val blackoutId: Long,
)
