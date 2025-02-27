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
                region = OblastType.Cherkasy,
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
                    ),
                    ShiftDto(
                        end = "12:00",
                        start = "13:00",
                        queues = listOf(
                            QueueDto(
                                queue = "1",
                                lightStatus = -1
                            )
                        )
                    ),
                    ShiftDto(
                        end = "10:00",
                        start = "15:00",
                        queues = listOf(
                            QueueDto(
                                queue = "1",
                                lightStatus = 1
                            )
                        )
                    ),
                    ShiftDto(
                        end = "16:00",
                        start = "17:00",
                        queues = listOf(
                            QueueDto(
                                queue = "2",
                                lightStatus = 1
                            )
                        )
                    )
                )
            )
        )
    )

    val dummyOblastResponseDtoDto = OblastResponseDto(
        oblasts = listOf(
            OblastDto(
                id = 1,
                oblastType = OblastType.Cherkasy,
                queues = listOf(
                    "1",
                    "2",
                    "3"
                )

            ),
            OblastDto(
                id = 2,
                oblastType = OblastType.Sumy,
                queues = listOf(
                    "1.1",
                    "2.1",
                    "3.1"
                )

            ),
            OblastDto(
                id = 3,
                oblastType = OblastType.Odesa,
                queues = listOf(
                    "1.3",
                    "2.3",
                    "3.3"
                )

            )
        )
    )
}