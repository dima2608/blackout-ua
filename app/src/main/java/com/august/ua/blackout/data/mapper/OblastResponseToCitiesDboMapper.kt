package com.august.ua.blackout.data.mapper

import com.august.ua.blackout.data.dto.OblastResponseDto
import com.august.ua.blackout.data.local.db.dbo.with_embeded.CityDbo

class OblastResponseToCitiesDboMapper(data: OblastResponseDto) :
    Mapper<OblastResponseDto, List<CityDbo>>(data) {

        override fun transform(): List<CityDbo> {
        return data?.oblasts?.map {
            CityDbo(
                id = it.id,
                oblastType = it.oblastType,
                queues = it.queues
            )
        }.orEmpty()
    }

}