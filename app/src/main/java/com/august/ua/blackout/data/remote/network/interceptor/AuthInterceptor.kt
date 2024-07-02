package com.august.ua.blackout.data.remote.network.interceptor

import android.content.SharedPreferences
import com.august.ua.blackout.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthKeyInterceptor(
    private val userAgent: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = chain.request().newBuilder()

        requestBuilder
            .header("Accept", "application/json")
            .header("api-key", "key-value")
            .addHeader("User-Agent", userAgent)
            .method(original.method, original.body)

        return chain.proceed(requestBuilder.build())
    }
}