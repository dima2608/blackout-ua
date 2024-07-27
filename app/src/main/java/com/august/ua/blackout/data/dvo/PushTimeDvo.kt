package com.august.ua.blackout.data.dvo

import com.august.ua.blackout.data.dto.OutragePushTime

data class PushTimeDvo(
    val type: OutragePushTime,
    val isSelected: Boolean = false
)