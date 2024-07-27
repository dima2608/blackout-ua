package com.august.ua.blackout.data.repository

import com.august.ua.blackout.R
import com.august.ua.blackout.data.dto.UserDto
import com.august.ua.blackout.data.dto.toUserDbo
import com.august.ua.blackout.data.local.db.dao.UserDao
import com.august.ua.blackout.data.remote.datasource.UserDataSource
import com.august.ua.blackout.data.remote.network.toResultState
import com.august.ua.blackout.domain.ResultState
import com.august.ua.blackout.domain.dvo.ErrorDvo
import com.august.ua.blackout.domain.repository.user.UserRepository
import com.august.ua.blackout.domain.repository.user.UserRepositoryAdaptor
import kotlinx.coroutines.flow.Flow

class UpdateUser(
    private val userDataSource: UserDataSource,
    private val userDao: UserDao
): UserRepositoryAdaptor<UserDto> {

    override suspend fun updateExistingUser(user: UserDto): ResultState<Any> {
        userDao.update(user.toUserDbo())
        return userDataSource.updateUser(user).toResultState { it }
    }

    override suspend fun createUser(user: UserDto): ResultState<Any> {
        return ResultState.Error(errorDvo = ErrorDvo(errorRes = R.string.something_went_wrong))
    }
}