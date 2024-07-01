package com.august.ua.blackout.domain.repository

import com.august.ua.blackout.domain.ResultState

interface BlackoutRepository {
    suspend fun getOblast(): ResultState<Any>
}