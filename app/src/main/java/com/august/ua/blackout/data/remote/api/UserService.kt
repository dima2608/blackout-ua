package com.august.ua.blackout.data.remote.api

import com.august.ua.blackout.data.remote.network.NetworkResult
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.HTTP
import retrofit2.http.POST

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
}