package com.august.ua.blackout.data.remote.datasource

import com.august.ua.blackout.data.dto.OblastDto
import com.august.ua.blackout.data.dto.OblastResponseDto
import com.august.ua.blackout.data.remote.network.NetworkResult

interface BlackoutDataSource {
    suspend fun getOblasts(): NetworkResult<List<OblastDto>>
}