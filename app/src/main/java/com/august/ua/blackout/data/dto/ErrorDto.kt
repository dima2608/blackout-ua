package com.august.ua.blackout.data.dto

import com.august.ua.blackout.domain.Entity
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.august.ua.blackout.data.dto.deserializer.ErrorDtoDeserializer

@JsonAdapter(ErrorDtoDeserializer::class)
data class ErrorDto(
    @SerializedName("statusCode")
    val code: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("error")
    val error: String?,
) : Entity