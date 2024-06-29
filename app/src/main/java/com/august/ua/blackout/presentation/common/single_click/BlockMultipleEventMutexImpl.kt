package com.august.ua.blackout.presentation.common.single_click

import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class BlockMultipleEventMutexImpl: BlockMultipleEventMutex {

    private val clickMutex = Mutex()

    override suspend fun blockEvent(processEvent: () -> Unit) {
        if (clickMutex.isLocked) return
        clickMutex.withLock {
            processEvent()
            delay(DELAY)
        }
    }
    companion object {
        private const val DELAY = 1L
    }
}