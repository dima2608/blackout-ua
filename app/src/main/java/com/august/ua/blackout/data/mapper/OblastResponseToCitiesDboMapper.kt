package com.august.ua.blackout.data.mapper

import com.august.ua.blackout.data.dto.OblastResponse
import com.august.ua.blackout.data.local.db.dbo.with_embeded.CityDbo

class OblastResponseToCitiesDboMapper(data: OblastResponse) :
    Mapper<OblastResponse, List<CityDbo>>(data) {

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