package com.august.ua.blackout.domain.repository

import com.august.ua.blackout.data.dto.OblastResponseDto
import com.august.ua.blackout.data.dto.OutragesResponseDto
import com.august.ua.blackout.domain.ResultState

interface BlackoutRepository {
    suspend fun getOblast(): ResultState<Any>
    suspend fun saveOutrages(outrage: OutragesResponseDto)
    suspend fun saveCities(cities: OblastResponseDto)
}