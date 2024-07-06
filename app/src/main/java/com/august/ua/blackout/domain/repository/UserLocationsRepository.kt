package com.august.ua.blackout.domain.repository

import androidx.paging.PagingSource
import com.august.ua.blackout.data.local.db.dbo.UserLocationDbo

interface UserLocationsRepository {
    suspend fun getLocationsPaging(): PagingSource<Int, UserLocationDbo>?
    suspend fun getLocationsQueue(): List<String?>
    suspend fun insert(userLocationDbo: UserLocationDbo)
    suspend fun deleteUserLocationById(locationId: Long)
}