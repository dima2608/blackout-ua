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
    @Transient
    val deviceId: String? = null,
    @SerializedName("fcmToken")
    @Transient
    val fcmToken: String? = null,
    @SerializedName("locations")
    val locations: List<LocationDto>? = null,
    @SerializedName("isAllPushTurnOn")
    var isAllPushTurnOn: Boolean = false,
    @SerializedName("isOutrageUpdatePushOn")
    var isOutrageUpdatePushOn: Boolean = false,
    @SerializedName("isNextDayOutragePushOn")
    var isNextDayOutragePushOn: Boolean = false,
) : Parcelable, Entity

fun UserDto.toUserDbo() = UserDbo(
    id = id ?: "",
    isAllPushTurnOn  = isAllPushTurnOn,
    isOutrageUpdatePushOn = isOutrageUpdatePushOn,
    isNextDayOutragePushOn = isNextDayOutragePushOn,
)
