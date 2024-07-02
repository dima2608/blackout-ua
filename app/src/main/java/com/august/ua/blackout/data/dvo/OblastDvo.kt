package com.august.ua.blackout.data.dvo

import com.august.ua.blackout.data.dto.OblastType

data class OblastDvo(
    var oblastType: OblastType,
    val queue: List<String>
)