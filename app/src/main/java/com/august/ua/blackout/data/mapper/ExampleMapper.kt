package com.august.ua.blackout.data.mapper

import com.august.ua.blackout.data.dto.UserDto
import com.august.ua.blackout.data.local.db.dbo.UserDbo

class ExampleMapper(data: UserDto): Mapper<UserDto, UserDbo>(data) {
    override fun transform(): UserDbo {
        TODO("Not yet implemented")
    }
}