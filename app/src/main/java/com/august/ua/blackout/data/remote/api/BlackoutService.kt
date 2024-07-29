package com.august.ua.blackout.data.remote.api


import com.august.ua.blackout.data.dto.OblastDto
import com.august.ua.blackout.data.dto.OblastResponseDto
import com.august.ua.blackout.data.dto.OblastType
import com.august.ua.blackout.data.dto.OutrageSearchDto
import com.august.ua.blackout.data.dto.OutragesResponseDto
import com.august.ua.blackout.data.dto.UserDto
import com.august.ua.blackout.data.remote.network.NetworkResult
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface BlackoutService {

    private companion object {
        private const val BASE_PATH = "/"
    }

    @GET("${BASE_PATH}city")
    suspend fun getOblasts(): NetworkResult<List<OblastDto>>

    @GET("${BASE_PATH}outrage")
    suspend fun getOutrage(
        @Query("date") date: String,
        @Query("queues") queue: List<String>,
        @Query("final") final: Boolean = true,
        @Query("region") oblastType: OblastType
    ): NetworkResult<OutragesResponseDto>

    @POST("${BASE_PATH}user")
    @FormUrlEncoded
    suspend fun crateUser(
        @Body user: UserDto?
    ): NetworkResult<OutragesResponseDto>

    @POST("${BASE_PATH}outrage/search")
    suspend fun getOutrageSearch(
        @Body body: OutrageSearchDto?
    ): NetworkResult<OutragesResponseDto>
}