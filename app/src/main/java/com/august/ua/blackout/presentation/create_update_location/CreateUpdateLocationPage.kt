package com.august.ua.blackout.presentation.create_update_location

enum class CreateUpdateLocationPage(
    pageNumber: Int
) {
    Undefined(pageNumber = -1),
    Location(pageNumber = 0),
    Queue(pageNumber = 1),
    LocationSettings(pageNumber = 2),
    Push(pageNumber = 3)
}