package com.august.ua.blackout.data.repository

import androidx.paging.PagingSource
import com.august.ua.blackout.data.local.db.dao.UserLocationDao
import com.august.ua.blackout.data.local.db.dao.UserLocationOutrageDao
import com.august.ua.blackout.data.local.db.dbo.UserLocationDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.UserLocationOutrageDbo
import com.august.ua.blackout.domain.repository.UserLocationsRepository

class UserLocationsRepositoryImpl(
    private val userLocationDao: UserLocationDao,
    private val userLocationOutrageDao: UserLocationOutrageDao
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

    override suspend fun saveUserLocationLocal(location: UserLocationOutrageDbo): Long {
        return userLocationOutrageDao.insert(location)
    }

    override suspend fun getLocationsTableSize(): Int {
        return userLocationOutrageDao.getLocationsSize()
    }
}