package com.august.ua.blackout.domain.repository.user

import com.august.ua.blackout.domain.ResultState

interface UserRepositoryAdaptor<D> {
    suspend fun updateExistingUser(user: D): ResultState<Any>
    suspend fun createUser(user: D): ResultState<Any>
}