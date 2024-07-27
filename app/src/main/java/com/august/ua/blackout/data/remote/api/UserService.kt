package com.august.ua.blackout.data.remote.api

import com.august.ua.blackout.data.dto.UserDto
import com.august.ua.blackout.data.remote.network.NetworkResult
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.HTTP
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    private companion object {
        private const val BASE_PATH = "app/user"
    }

    @FormUrlEncoded
    @POST("${BASE_PATH}/fcm-tokens")
    suspend fun sendFcmToken(
        @Field("deviceId") deviceId: String,
        @Field("token") token: String
    ): NetworkResult<Unit>

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "$BASE_PATH/fcm-tokens-with-id", hasBody = true)
    suspend fun clearFcmToken(
        @Field("deviceId") deviceId: String,
        @Field("userId") userId: String
    ): NetworkResult<Unit>

    @FormUrlEncoded
    @PATCH("${BASE_PATH}/user/{id}")
    suspend fun updateUser(
        @Path("id") userId: String,
        @Body user: UserDto?
    ): NetworkResult<Unit>

    @FormUrlEncoded
    @POST("${BASE_PATH}/user")
    suspend fun createUser(
        @Body user: UserDto?
    ): NetworkResult<UserDto>
}