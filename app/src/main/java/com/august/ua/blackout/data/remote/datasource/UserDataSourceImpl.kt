package com.august.ua.blackout.data.remote.datasource

import com.august.ua.blackout.data.remote.api.UserService
import com.august.ua.blackout.data.remote.network.NetworkResult

class UserDataSourceImpl(
    private val userService: UserService,
): UserDataSource {

    override suspend fun sendFcmToken(
        deviceId: String,
        token: String
    ): NetworkResult<Unit> {
        return userService.sendFcmToken(deviceId = deviceId, token = token)
    }

    override suspend fun clearFcmToken(
        deviceId: String,
        userId: String
    ): NetworkResult<Unit> {
        return userService.clearFcmToken(
            deviceId = deviceId,
            userId = userId
        )
    }
}