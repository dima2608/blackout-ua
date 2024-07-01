package com.august.ua.blackout.data.remote.datasource

import com.august.ua.blackout.data.dto.OblastResponse
import com.august.ua.blackout.data.remote.network.NetworkResult

interface BlackoutDataSource {
    suspend fun getOblasts(): NetworkResult<OblastResponse>
}