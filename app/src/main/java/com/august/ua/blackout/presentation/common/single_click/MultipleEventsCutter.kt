package com.august.ua.blackout.presentation.common.single_click

internal interface MultipleEventsCutter {
    fun processEvent(event: () -> Unit, delay: Long)

    companion object
}

internal fun MultipleEventsCutter.Companion.get(): MultipleEventsCutter =
    MultipleEventsCutterImpl()

private class MultipleEventsCutterImpl : MultipleEventsCutter {
    private val now: Long
        get() = System.currentTimeMillis()

    private var lastEventTimeMs: Long = 0

    override fun processEvent(event: () -> Unit, delay: Long) {
        if (now - lastEventTimeMs >= delay) {
            event.invoke()
        }
        lastEventTimeMs = now
    }
}