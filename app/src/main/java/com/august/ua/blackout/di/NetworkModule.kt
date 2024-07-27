package com.august.ua.blackout.di

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import com.august.ua.blackout.BuildConfig
import com.august.ua.blackout.BuildConfig.BASE_URL
import com.august.ua.blackout.data.remote.network.NetworkResultCallAdapterFactory
import com.august.ua.blackout.data.remote.network.interceptor.AuthKeyInterceptor
import com.pluto.plugins.network.okhttp.PlutoOkhttpInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor() = AuthKeyInterceptor(
        userAgent = buildUserAgent()
    )

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(EnumConverterFactory())
            .addCallAdapterFactory(NetworkResultCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authKeyInterceptor: AuthKeyInterceptor,
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.apply {
            callTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)

            addNetworkInterceptor { chain ->
                val original = chain.request()

                val requestBuilder = chain.request().newBuilder()
                requestBuilder
                    .header("Accept", "application/json")
                    .addHeader("platform", "android")
                    .addHeader("User-Agent", buildUserAgent())
                    .method(original.method, original.body)
                return@addNetworkInterceptor chain.proceed(requestBuilder.build())
            }
            if (BuildConfig.DEBUG) {
                addInterceptor(prepareLoggingInterceptor())
                addInterceptor(PlutoOkhttpInterceptor)
            }
        }
        builder.addInterceptor(authKeyInterceptor)

        return builder.build()
    }

    private fun buildUserAgent(): String {
        val versionName = BuildConfig.VERSION_NAME
        val versionCode = BuildConfig.VERSION_CODE
        val deviceModel = Build.MODEL
        val deviceBrand = Build.MANUFACTURER
        return "Version: $versionName($versionCode);  Model: $deviceModel; Brand: $deviceBrand"
    }

    private fun prepareLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            private fun print(m: String) {
                Log.i("ApiFactory", "\n$m")
            }

            override fun log(message: String) {
                if (message.startsWith("{") || message.startsWith("[")) try {
                    JSONObject(message).toString(4).also(::print)
                } catch (e: JSONException) {
                    print(message)
                }
                else print(message)
            }
        }).also { it.level = HttpLoggingInterceptor.Level.BODY }
    }
}

private const val DEFAULT_TIMEOUT = 120
const val CONNECT_TIMEOUT = DEFAULT_TIMEOUT
const val WRITE_TIMEOUT = DEFAULT_TIMEOUT
const val READ_TIMEOUT = DEFAULT_TIMEOUT
const val SHARED_PREFERENCES_NAME = "blackout-ua-prefs"