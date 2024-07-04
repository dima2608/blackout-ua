package com.august.ua.blackout.data.mapper

abstract class Mapper<SRC, DST>(val data: SRC?) : IMapper<DST>