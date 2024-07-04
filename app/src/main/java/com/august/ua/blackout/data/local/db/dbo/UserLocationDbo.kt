package com.august.ua.blackout.data.local.db.dbo

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.august.ua.blackout.data.dto.OblastType

@Entity(tableName = "user_location")
data class UserLocationDbo(
    @PrimaryKey(autoGenerate = true)
    var locationId: Long = 0,
    @ColumnInfo("locationOrder")
    var locationOrder: Int,
//    @ColumnInfo("parentUserId")
//    var parentUserId: Long,
    @ColumnInfo("blackout_id")
    val blackoutId: Long,
    @ColumnInfo("user_location_name")
    var locationName: String,
    @ColumnInfo("selected_location_icon_id")
    var selectedLocationIconId: Long,
    @ColumnInfo("selected_location_color_id")
    val selectedColorId: Long,
    @ColumnInfo("selected_location")
    val selectedLocation: OblastType,
    @ColumnInfo("selected_queue")
    val selectedQueue: String,
    @ColumnInfo("is_location_push_enabled")
    val isLocationPushEnabled: Boolean,
)
