package com.august.ua.blackout.presentation.home.calendar_tab.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.august.ua.blackout.domain.common.parseCalendarHourNumber
import com.august.ua.blackout.ui.theme.BlackoutTextStyle
import com.august.ua.blackout.ui.theme.BlackoutUaTheme
import java.time.LocalTime

@Composable
fun BasicSidebarLabel(
    time: LocalTime,
    modifier: Modifier = Modifier,
) {
    Text(
        text = time.parseCalendarHourNumber(),
        modifier = modifier
            .fillMaxHeight(),
        style = BlackoutTextStyle.t5TextSmallDescription,
        textAlign = TextAlign.Center,
    )
}

@Preview(showBackground = true)
@Composable
fun BasicSidebarLabelPreview() {
    BlackoutUaTheme {
        BasicSidebarLabel(time = LocalTime.NOON, Modifier.sizeIn(maxHeight = 64.dp))
    }
}