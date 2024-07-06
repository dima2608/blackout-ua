package com.august.ua.blackout.data.mapper

import com.august.ua.blackout.data.dto.OutragesResponseDto
import com.august.ua.blackout.data.dto.QueueDto
import com.august.ua.blackout.data.dto.ShiftDto
import com.august.ua.blackout.data.local.db.dbo.with_embeded.OutrageDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.OutrageFullDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.OutrageQueueDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.OutrageShiftDbo

class OutragesResponseDtoToOutrageFullDboMapper(data: OutragesResponseDto) :
    Mapper<OutragesResponseDto, OutrageFullDbo>(data) {

    override fun transform(): OutrageFullDbo {
        return OutrageFullDbo(
            lastUpdate = data?.lastUpdate,
            accessDate = data?.accessDate,
            outrages = buildOutrages()
        )
    }

    private fun buildOutrages() = data?.outrages?.map {
        OutrageDbo(
            date = it.date ?: "",
            changeCount = it.changeCount,
            region = it.region,
            type = it.type,
            shifts = it.shifts?.buildShifts()
        )
    }

    private fun List<ShiftDto>?.buildShifts() = this?.map {
        OutrageShiftDbo(
            start = it.start,
            end = it.end,
            queues = it.queues?.buildQueues()
        )
    }

    private fun List<QueueDto>?.buildQueues() = this?.map {
        OutrageQueueDbo(
            queue = it.queue ?: "",
            lightStatus = it.lightStatus
        )
    }
}