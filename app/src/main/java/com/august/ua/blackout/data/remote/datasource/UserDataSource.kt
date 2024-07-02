package com.august.ua.blackout.data.remote.datasource

import com.august.ua.blackout.data.remote.network.NetworkResult

interface UserDataSource {
    suspend fun sendFcmToken(deviceId: String, token: String): NetworkResult<Unit>
    suspend fun clearFcmToken(
        deviceId: String,
        userId: String
    ): NetworkResult<Unit>
}