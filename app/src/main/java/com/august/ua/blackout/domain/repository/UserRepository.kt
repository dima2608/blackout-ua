package com.august.ua.blackout.domain.repository

import com.august.ua.blackout.data.dto.OblastType
import com.august.ua.blackout.domain.ResultState

interface UserRepository<T, D> {

    val userData: T

    suspend fun saveNewUserData(user: D)

    suspend fun saveOblast(oblastType: OblastType)
    suspend fun saveQueue(queue: String)

    suspend fun clearUserLocalData()
    suspend fun clearUserData()

    suspend fun sendFcmToken(deviceId: String, token: String): ResultState<Unit>

    suspend fun clearFcmToken(
        deviceId: String,
        userId: String
    ): ResultState<Unit>
}
