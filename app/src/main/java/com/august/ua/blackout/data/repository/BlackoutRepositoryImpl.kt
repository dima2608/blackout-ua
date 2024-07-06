package com.august.ua.blackout.data.repository

import com.august.ua.blackout.data.dto.OblastResponse
import com.august.ua.blackout.data.dto.OutrageDto
import com.august.ua.blackout.data.dto.OutragesResponseDto
import com.august.ua.blackout.data.local.db.dao.CityDao
import com.august.ua.blackout.data.local.db.dao.OutrageDao
import com.august.ua.blackout.data.mapper.OblastResponseToCitiesDboMapper
import com.august.ua.blackout.data.mapper.OutragesResponseDtoToOutrageFullDboMapper
import com.august.ua.blackout.data.remote.api.BlackoutService
import com.august.ua.blackout.data.remote.network.toResultState
import com.august.ua.blackout.domain.ResultState
import com.august.ua.blackout.domain.repository.BlackoutRepository

class BlackoutRepositoryImpl(
    private val blackoutService: BlackoutService,
    private val outrageDao: OutrageDao,
    private val cityDao: CityDao,
): BlackoutRepository {
    override suspend fun getOblast(): ResultState<Any> {
        return blackoutService.getOblasts().toResultState { result -> result }
    }

    override suspend fun saveOutrages(outrage: OutragesResponseDto) {
        outrageDao.insert(
            outrage = OutragesResponseDtoToOutrageFullDboMapper(outrage).transform()
        )
    }

    override suspend fun saveCities(cities: OblastResponse) {
        cityDao.insert(
            cites = OblastResponseToCitiesDboMapper(cities).transform()
        )
    }

    suspend fun saveLocations() {

    }
}