package com.august.ua.blackout.data.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import com.august.ua.blackout.R
import com.august.ua.blackout.data.dvo.ElectricityStatus
import com.august.ua.blackout.data.dvo.LocationDvo
import com.august.ua.blackout.data.local.db.dao.UserLocationDao
import com.august.ua.blackout.data.local.db.dao.UserLocationOutrageDao
import com.august.ua.blackout.data.local.db.dbo.UserLocationDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.UserLocationOutrageDbo
import com.august.ua.blackout.data.mapper.UserLocationOutrageDboToLocationDvoMapper
import com.august.ua.blackout.domain.repository.UserLocationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserLocationsRepositoryImpl(
    private val userLocationDao: UserLocationDao,
    private val userLocationOutrageDao: UserLocationOutrageDao,
    private val context: Context,
): UserLocationsRepository {
//    override fun getLocationsPaging(): PagingSource<Int, UserLocationDbo>? {
//        return userLocationDao.getLocationsQueuePaging()
//    }
//
//    override suspend fun getLocationsQueue(): List<String?> {
//        return  userLocationDao.getQueues()
//    }
//
//    override suspend fun insert(userLocationDbo: UserLocationDbo) {
//        userLocationDao.insert(userLocationDbo)
//    }
//
//    override suspend fun deleteUserLocationById(locationId: Long) {
//        userLocationDao.deleteUserLocationById(locationId)
//    }

    override suspend fun saveUserLocationLocal(location: UserLocationOutrageDbo): Long {
        return userLocationOutrageDao.insert(location)
    }

    override suspend fun getLocationsTableSize(): Int {
        return userLocationOutrageDao.getLocationsSize()
    }

    override suspend fun getLocationsOutragePaging(): Flow<PagingData<LocationDvo>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 2),
            pagingSourceFactory = {
                userLocationOutrageDao.getLocationsOutragePaging()
            }
        ).flow.map { pagingData ->
            pagingData.map { entity ->
                UserLocationOutrageDboToLocationDvoMapper(entity, context).transform()
            }
        }
    }

}