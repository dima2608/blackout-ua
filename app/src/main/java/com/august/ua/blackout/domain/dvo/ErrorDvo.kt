package com.august.ua.blackout.domain.dvo

import com.august.ua.blackout.domain.common.EMPTY_STRING

data class ErrorDvo(
    val errorRes: Int? = null,
    val errorMessage: String? = null,
    val errorCode: String? = null
) {
    override fun toString(): String {
        return when {
            errorMessage != null -> errorMessage
            errorRes != null -> errorRes.toString()
            else -> EMPTY_STRING
        }
    }
}