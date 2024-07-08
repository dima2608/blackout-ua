package com.august.ua.blackout.domain.use_kase

import com.august.ua.blackout.domain.ResultState
import com.august.ua.blackout.domain.repository.BlackoutRepository

class GetOblastsUseCase(
    private val blackoutRepository: BlackoutRepository
) {
    suspend operator fun invoke(complexId: String, date: String): ResultState<Any> {
        return blackoutRepository.getOblasts()
    }
}