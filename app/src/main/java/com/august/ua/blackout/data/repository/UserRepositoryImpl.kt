package com.august.ua.blackout.data.repository

import android.app.Application
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import com.august.ua.blackout.R
import com.august.ua.blackout.data.dto.ErrorDto
import com.august.ua.blackout.data.dto.OblastType
import com.august.ua.blackout.data.dto.UserDto
import com.august.ua.blackout.data.dto.toUserDbo
import com.august.ua.blackout.data.local.db.dao.UserDao
import com.august.ua.blackout.data.local.db.dbo.UserDbo
import com.august.ua.blackout.data.local.db.dbo.toUserDto
import com.august.ua.blackout.data.mapper.UserWithAllLocationsToUserDtoMapper
import com.august.ua.blackout.data.remote.datasource.UserDataSource
import com.august.ua.blackout.data.remote.network.NetworkResult
import com.august.ua.blackout.data.remote.network.toResultState
import com.august.ua.blackout.domain.ResultState
import com.august.ua.blackout.domain.dvo.ErrorDvo
import com.august.ua.blackout.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val userDataStorePreferences: DataStore<UserDto?>,
    private val sharedPreferences: SharedPreferences,
    private val userDataSource: UserDataSource,
    private val userDao: UserDao,
    private val updateUser: UpdateUser,
): UserRepository<Flow<UserDto?>, UserDto> {

    override val userData: Flow<UserDto?> = userDao.observeUser().map { it?.toUserDto() }

    override suspend fun saveNewUserData(user: UserDto) {
        userDao.update(user.toUserDbo())
    }

    override suspend fun saveOblast(oblastType: OblastType) {
//        userDataStorePreferences.updateData {
//            it?.copy(
//                oblastType = oblastType
//            )
//        }
    }

    override suspend fun saveQueue(queue: String) {
//        userDataStorePreferences.updateData {
//            it?.copy(
//                queue = queue
//            )
//        }
    }

    override suspend fun clearUserLocalData() {
        clearUserData()
        //sharedPreferences.clear()
    }

    override suspend fun clearUserData() {
        userDataStorePreferences.updateData {
            null
        }
    }

    override suspend fun sendFcmToken(deviceId: String, token: String): ResultState<Unit> {
        return userDataSource.sendFcmToken(deviceId = deviceId, token = token).toResultState { it }
    }

    override suspend fun clearFcmToken(
        deviceId: String,
        userId: String
    ): ResultState<Unit> {
        return userDataSource.clearFcmToken(
            deviceId = deviceId,
            userId = userId
        ).toResultState { it }
    }

    override suspend fun updateUser(): ResultState<Any> {
        val user = getUserWithLocations()
        if (user.id.isNullOrBlank()) return ResultState.Error(ErrorDvo(R.string.something_went_wrong))
        return userDataSource.updateUser(userId = user.id, user = user).toResultState { it }
    }

    override suspend fun getUser(): UserDbo? {
        return userDao.getUser()
    }

    override suspend fun getUserWithLocations(): UserDto {
        val userWithLocations = userDao.getUserWithLocations()
        return UserWithAllLocationsToUserDtoMapper(userWithLocations).transform()
    }

    override suspend fun createUser(user: UserDto): ResultState<Any> {
        return userDataSource.createUser(user).toResultState { it }
    }

}