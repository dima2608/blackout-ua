package com.august.ua.blackout.domain.repository

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.august.ua.blackout.data.dvo.LocationDvo
import com.august.ua.blackout.data.local.db.dbo.UserLocationDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.UserLocationOutrageDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.UserLocationShiftDbo
import com.august.ua.blackout.domain.common.getCurrentShiftDate
import kotlinx.coroutines.flow.Flow

interface UserLocationsRepository {
//    fun getLocationsPaging(): PagingSource<Int, UserLocationDbo>?
//    suspend fun getLocationsQueue(): List<String?>
//    suspend fun insert(userLocationDbo: UserLocationDbo)
//    suspend fun deleteUserLocationById(locationId: Long)
    suspend fun saveUserLocationLocal(location: UserLocationOutrageDbo): Long
    suspend fun getLocationsTableSize(): Int

    suspend fun getLocationsOutragePaging(shiftDate: String = getCurrentShiftDate()): Flow<PagingData<LocationDvo>>
    suspend fun saveLocationShifts(shifts: List<UserLocationShiftDbo>, date: String)
}