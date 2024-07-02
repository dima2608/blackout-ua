package com.august.ua.blackout.data.dto

import com.august.ua.blackout.domain.Entity

data class OblastDto(
    val oblastType: OblastType,
    val queues: List<String>
): Entity