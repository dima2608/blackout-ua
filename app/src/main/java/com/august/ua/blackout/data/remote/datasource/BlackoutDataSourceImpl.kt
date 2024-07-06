package com.august.ua.blackout.data.remote.datasource

import com.august.ua.blackout.data.dto.OblastResponseDto
import com.august.ua.blackout.data.remote.api.BlackoutService
import com.august.ua.blackout.data.remote.network.NetworkResult

class BlackoutDataSourceImpl(
    private val blackoutService: BlackoutService
): BlackoutDataSource {
    override suspend fun getOblasts(): NetworkResult<OblastResponseDto> {
        return blackoutService.getOblasts()
    }

}