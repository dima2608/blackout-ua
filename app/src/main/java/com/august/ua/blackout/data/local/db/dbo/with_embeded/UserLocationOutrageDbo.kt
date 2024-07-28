package com.august.ua.blackout.data.local.db.dbo.with_embeded

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.august.ua.blackout.data.dto.OblastType
import com.august.ua.blackout.data.dto.OutragePushTime
import com.august.ua.blackout.data.dvo.LocationColorType
import com.august.ua.blackout.data.dvo.LocationIconType
import com.google.gson.annotations.Expose

@Entity(tableName = "user_location_outrage_table")
data class UserLocationOutrageDbo(
    @PrimaryKey(autoGenerate = true)
    var locationId: Long = 0,
    @ColumnInfo("user_id")
    var userId: String,
    @ColumnInfo("location_order")
    var locationOrder: Int,
    @ColumnInfo("user_location_name", defaultValue = "")
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
    @ColumnInfo("is_outrage_push_enable")
    val isOutragePushEnabled: Boolean,
    @ColumnInfo("date")
    val date: String,
    @ColumnInfo("selected_push_time")
    val selectedPushTime: List<OutragePushTime>?,

//    @ColumnInfo("shifts")
//    @Expose
//    val shifts: List<UserLocationShiftDbo>,
)
