package com.august.ua.blackout.data.dto

enum class OutragePushTime(
    timeMin: Int,
    val timeString: String
){
    FifteenMin(15, "15"),
    ThirtyMin(30, "30"),
    Hour(60, "1")
}