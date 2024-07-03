package com.august.ua.blackout.data.dto

import com.august.ua.blackout.domain.Entity

data class OblastResponse(
    val oblasts: List<OblastDto>?
): Entity