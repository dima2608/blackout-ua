package com.august.ua.blackout.presentation.common.single_click

interface BlockMultipleEventMutex {
    suspend fun blockEvent(processEvent: () -> Unit)
}