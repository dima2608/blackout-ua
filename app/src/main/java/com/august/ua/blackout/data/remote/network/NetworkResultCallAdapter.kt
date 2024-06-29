package com.august.ua.blackout.data.remote.network

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class NetworkResultCallAdapter<T : Any>(
    private val resultType: Type
) : CallAdapter<T, Call<NetworkResult<T>>> {

    override fun responseType(): Type = resultType

    override fun adapt(call: Call<T>): Call<NetworkResult<T>> {
        return NetworkResultCall(call)
    }
}