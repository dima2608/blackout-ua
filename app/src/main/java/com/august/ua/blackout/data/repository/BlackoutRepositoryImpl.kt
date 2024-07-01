package com.august.ua.blackout.data.repository

import com.august.ua.blackout.data.remote.api.BlackoutService
import com.august.ua.blackout.data.remote.network.toResultState
import com.august.ua.blackout.domain.ResultState
import com.august.ua.blackout.domain.repository.BlackoutRepository

class BlackoutRepositoryImpl(
    private val blackoutService: BlackoutService
): BlackoutRepository {
    override suspend fun getOblast(): ResultState<Any> {
        return blackoutService.getOblasts().toResultState { result -> result }
    }
}