package com.august.ua.blackout.presentation.home.calendar_tab.event

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class EventsProvider : PreviewParameterProvider<CalendarEvent> {
    override val values = sampleCalendarEvents.asSequence()
}