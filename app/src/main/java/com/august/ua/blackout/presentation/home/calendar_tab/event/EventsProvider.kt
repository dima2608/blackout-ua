package com.august.ua.blackout.presentation.home.calendar_tab.event

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.august.ua.blackout.presentation.home.calendar_tab.components.sampleCalendarEvents

class EventsProvider : PreviewParameterProvider<CalendarEvent> {
    override val values = sampleCalendarEvents.asSequence()
}