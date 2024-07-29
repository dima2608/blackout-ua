package com.august.ua.blackout.data.dto

import com.google.gson.annotations.SerializedName

enum class OutragePushTime(
    val timeMin: Int,
    val timeString: String
){
    @SerializedName("15")
    FifteenMin(15, "15"),
    @SerializedName("30")
    ThirtyMin(30, "30"),
    @SerializedName("60")
    Hour(60, "1")
}