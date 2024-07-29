package com.august.ua.blackout.domain.repository.user

import com.august.ua.blackout.data.dto.OblastType
import com.august.ua.blackout.data.dto.UserDto
import com.august.ua.blackout.data.local.db.dbo.UserDbo
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

    suspend fun updateUser(): ResultState<Any>
    suspend fun getUser(): UserDbo?
    suspend fun createUser(user: D): ResultState<Any>
    suspend fun getUserWithLocations(): UserDto?
    suspend fun saveIsAskNotificationScreenWasShown(wasSeen: Boolean)
}
