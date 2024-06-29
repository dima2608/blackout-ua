package com.august.ua.blackout.domain

import com.august.ua.blackout.domain.dvo.ErrorDvo

sealed class ResultState<out T : Any> {

    data class Success<out T : Any>(val data: T? = null) : ResultState<T>()
    data class Error(val errorDvo: ErrorDvo) : ResultState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$errorDvo]"
        }
    }
}