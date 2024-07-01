package com.august.ua.blackout.data.remote.api

import com.august.ua.blackout.data.dto.OblastResponse
import com.august.ua.blackout.data.remote.network.NetworkResult
import retrofit2.http.GET

interface BlackoutService {

    private companion object {
        private const val BASE_PATH = "app/"
    }

    @GET("${BASE_PATH}oblast")
    suspend fun getOblasts(): NetworkResult<OblastResponse>
}