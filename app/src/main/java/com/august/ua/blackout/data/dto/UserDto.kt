package com.august.ua.blackout.data.dto

import android.os.Parcelable
import androidx.room.ColumnInfo
import com.august.ua.blackout.data.local.db.dbo.UserDbo
import com.august.ua.blackout.domain.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDto(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("deviceId")
    val deviceId: String? = null,
    @SerializedName("fcmToken")
    val fcmToken: String? = null,
    @SerializedName("locations")
    val locations: List<LocationDto>? = null,
    @SerializedName("isPushEnabled")
    var isAllPushTurnOn: Boolean = true,
    @SerializedName("isPushUpdateOutrageEnabled")
    var isOutrageUpdatePushOn: Boolean = true,
    @SerializedName("isPushNextDayEnabled")
    var isNextDayOutragePushOn: Boolean = true,
) : Parcelable, Entity

fun UserDto.toUserDbo() = UserDbo(
    id = id ?: "",
    isAllPushTurnOn  = isAllPushTurnOn,
    isOutrageUpdatePushOn = isOutrageUpdatePushOn,
    isNextDayOutragePushOn = isNextDayOutragePushOn,
)
