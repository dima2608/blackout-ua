package com.august.ua.blackout.domain.repository

import com.august.ua.blackout.data.dto.Oblast
import com.august.ua.blackout.domain.ResultState
import com.google.common.collect.Queues

interface UserRepository<T, D> {

    val userData: T

    suspend fun saveNewUserData(user: D)

    suspend fun saveOblast(oblast: Oblast)
    suspend fun saveQueue(queue: String)

    suspend fun clearUserLocalData()
    suspend fun clearUserData()
}
