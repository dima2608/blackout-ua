package com.august.ua.blackout.data.dto

object Dummy {
    val dummyOutragesResponseDto = OutragesResponseDto(
        lastUpdate = "2024-07-02T20:25:18.005Z",
        accessDate = "2024-07-02T20:25:18.005Z",
        outrages = listOf(
            OutrageDto(
                type = OutrageStatus.Schedule,
                date = "2024-06-24T21:00:00.000Z",
                region = OblastType.Odesa,
                changeCount = 1,
                shifts = listOf(
                    ShiftDto(
                        end = "10:00",
                        start = "11:00",
                        queues = listOf(
                            QueueDto(
                                queue = "1",
                                lightStatus = 1
                            )
                        )
                    )
                )
            ),
            OutrageDto(
                type = OutrageStatus.Schedule,
                date = "2024-06-24T21:00:00.000Z",
                region = OblastType.Odesa,
                changeCount = 1,
                shifts = listOf(
                    ShiftDto(
                        end = "14:00",
                        start = "16:00",
                        queues = listOf(
                            QueueDto(
                                queue = "1",
                                lightStatus = 1
                            )
                        )
                    )
                )
            )
        )
    )
}