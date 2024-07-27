package com.august.ua.blackout.domain.repository

import com.august.ua.blackout.data.dto.OblastResponseDto
import com.august.ua.blackout.data.dto.OblastType
import com.august.ua.blackout.data.dto.OutragesResponseDto
import com.august.ua.blackout.data.dvo.CityDvo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.CityDbo
import com.august.ua.blackout.domain.ResultState

interface BlackoutRepository {
    suspend fun getOblasts(): ResultState<Any>
    suspend fun getOutrage(
        oblast: List<OblastType>,
        queue: List<String>
    ): ResultState<Any>
    suspend fun saveOutrages(outrage: OutragesResponseDto)
    suspend fun saveCities(cities: OblastResponseDto)
    suspend fun getCitiesLocal(): List<CityDvo>
}