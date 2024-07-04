package com.august.ua.blackout.data.mapper

interface IMapper<DST> {
    fun transform(): DST
}