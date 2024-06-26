package com.august.ua.blackout.data.local.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.august.ua.blackout.data.dto.UserDto
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import java.io.InputStream
import java.io.OutputStream

object UserPreferencesSerializer : Serializer<UserDto?> {

    override val defaultValue: UserDto? = null

    override suspend fun readFrom(input: InputStream): UserDto? {
        try {
            val gson = input.readBytes().decodeToString()
            val userDto: UserDto? =  Gson().fromJson(gson, UserDto::class.java)
            if (userDto?.id.isNullOrEmpty()) return null
            return userDto
        } catch (exception: SerializationException) {
            throw CorruptionException("Error occured during decoding UserDto the storage", exception)
        }
    }

    override suspend fun writeTo(
        t: UserDto?,
        output: OutputStream
    ) = withContext(Dispatchers.IO) {
        output.write(
            Gson().toJson(t).toByteArray()
        )
    }

}