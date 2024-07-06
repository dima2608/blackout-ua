package com.august.ua.blackout.data.repository

import androidx.paging.PagingSource
import com.august.ua.blackout.data.local.db.dao.UserLocationDao
import com.august.ua.blackout.data.local.db.dbo.UserLocationDbo
import com.august.ua.blackout.domain.repository.UserLocationsRepository

class UserLocationsRepositoryImpl(
    private val userLocationDao: UserLocationDao
): UserLocationsRepository {
    override fun getLocationsPaging(): PagingSource<Int, UserLocationDbo>? {
        return userLocationDao.getLocationsQueuePaging()
    }

    override suspend fun getLocationsQueue(): List<String?> {
        return  userLocationDao.getQueues()
    }

    override suspend fun insert(userLocationDbo: UserLocationDbo) {
        userLocationDao.insert(userLocationDbo)
    }

    override suspend fun deleteUserLocationById(locationId: Long) {
        userLocationDao.deleteUserLocationById(locationId)
    }
}