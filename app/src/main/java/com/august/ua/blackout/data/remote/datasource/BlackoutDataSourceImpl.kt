package com.august.ua.blackout.data.remote.datasource

import com.august.ua.blackout.data.dto.OblastDto
import com.august.ua.blackout.data.dto.OblastResponseDto
import com.august.ua.blackout.data.remote.api.BlackoutService
import com.august.ua.blackout.data.remote.network.NetworkResult

class BlackoutDataSourceImpl(
    private val blackoutService: BlackoutService
): BlackoutDataSource {
    override suspend fun getOblasts(): NetworkResult<List<OblastDto>> {
        return blackoutService.getOblasts()
    }

}