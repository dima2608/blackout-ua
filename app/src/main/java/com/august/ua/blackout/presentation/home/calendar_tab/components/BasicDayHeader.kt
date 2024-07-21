package com.august.ua.blackout.presentation.home.calendar_tab.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.august.ua.blackout.domain.common.parseCalendarDay
import com.august.ua.blackout.ui.theme.BlackoutUaTheme
import java.time.LocalDate

@Composable
fun BasicDayHeader(
    day: LocalDate,
    modifier: Modifier = Modifier,
) {
    Text(
        text = day.parseCalendarDay(),
        textAlign = TextAlign.Start,
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun BasicDayHeaderPreview() {
    BlackoutUaTheme {
        BasicDayHeader(day = LocalDate.now())
    }
}