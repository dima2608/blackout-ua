package com.august.ua.blackout.presentation.home.calendar_tab.event

import androidx.compose.ui.graphics.Color
import java.time.LocalDateTime

data class CalendarEvent(
    val name: String,
    val color: Color,
    val start: LocalDateTime,
    val end: LocalDateTime,
    val description: String? = null,
)
