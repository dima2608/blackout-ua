package com.august.ua.blackout.data.repository

import com.august.ua.blackout.data.dto.OblastResponseDto
import com.august.ua.blackout.data.dto.OutragesResponseDto
import com.august.ua.blackout.data.dvo.CityDvo
import com.august.ua.blackout.data.local.db.dao.CityDao
import com.august.ua.blackout.data.local.db.dao.OutrageDao
import com.august.ua.blackout.data.local.db.dbo.with_embeded.CityDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.toCityDvo
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
    override suspend fun getOblasts(): ResultState<Any> {
        return blackoutService.getOblasts().toResultState { result -> result }
    }

    override suspend fun saveOutrages(outrage: OutragesResponseDto) {
        outrageDao.insert(
            outrage = OutragesResponseDtoToOutrageFullDboMapper(outrage).transform()
        )
    }

    override suspend fun saveCities(cities: OblastResponseDto) {
        cityDao.update(
            cites = OblastResponseToCitiesDboMapper(cities).transform()
        )
    }

    override suspend fun getCitiesLocal(): List<CityDvo> {
        return cityDao.getCities().map { it.toCityDvo() }
    }

    suspend fun saveLocations() {

    }
}