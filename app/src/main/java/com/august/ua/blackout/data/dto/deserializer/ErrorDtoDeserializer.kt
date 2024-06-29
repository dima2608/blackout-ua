package com.august.ua.blackout.data.dto.deserializer

import com.august.ua.blackout.data.dto.ErrorDto
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ErrorDtoDeserializer: JsonDeserializer<ErrorDto> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ErrorDto {
        val jsonObject = json?.asJsonObject
        val isMessageArray = jsonObject?.get("message")?.isJsonArray
        val message = if (isMessageArray == true) jsonObject.get("message")?.asJsonArray?.asString else jsonObject?.get("message")?.asString
        return ErrorDto(
            code = jsonObject?.get("statusCode")?.asInt,
            message = message,
            error = jsonObject?.get("error")?.asString
        )
    }
}