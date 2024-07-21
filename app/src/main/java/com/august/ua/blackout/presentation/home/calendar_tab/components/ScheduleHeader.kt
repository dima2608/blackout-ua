package com.august.ua.blackout.presentation.home.calendar_tab.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.august.ua.blackout.domain.common.parseCalendarDayNumber
import com.august.ua.blackout.domain.common.parseCalendarDayOfWeek
import com.august.ua.blackout.ui.theme.Black
import com.august.ua.blackout.ui.theme.BlackoutTextStyle
import com.august.ua.blackout.ui.theme.BlackoutUaTheme
import com.august.ua.blackout.ui.theme.EggshellAlpha80
import com.august.ua.blackout.ui.theme.Violet
import com.august.ua.blackout.ui.theme.White
import java.time.LocalDate

@Composable
fun ScheduleHeader(
    modifier: Modifier = Modifier,
    sideBarWith: Int = 0,
) {
    val date = LocalDate.now()
    val dayOfWeek = date.parseCalendarDayOfWeek()
    val dayNumber = date.parseCalendarDayNumber()
    val dividerColor = Black.copy(0.25f)
    val sideBarWidth: Dp =
        with(LocalDensity.current) { (sideBarWith + VERTICAL_LINE_PADDING).toDp() }

    val paddingBottom = 16
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                EggshellAlpha80,
                MaterialTheme.shapes.medium.copy(
                    topEnd = CornerSize(0.dp),
                    topStart = CornerSize(0.dp)
                )
            )
    ) {
        Column(
            modifier = modifier
                .width(sideBarWidth)
                .drawBehind {
                    drawLine(
                        dividerColor,
                        start = Offset((sideBarWith.toFloat() + VERTICAL_LINE_PADDING), 0f),
                        end = Offset(
                            (sideBarWith.toFloat() + VERTICAL_LINE_PADDING),
                            size.height + OFFSET + paddingBottom
                        ),
                        strokeWidth = 1.dp.toPx()
                    )
                }
                .padding(bottom = paddingBottom.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(bottom = 2.dp),
                text = dayOfWeek,
                textAlign = TextAlign.Center,
                style = BlackoutTextStyle.t5TextSmallDescription,
                color = Violet
            )

            Box(
                modifier = Modifier
                    .size(35.dp)
                    .background(Violet, CircleShape),
                contentAlignment = Alignment.Center

            ) {
                Text(
                    text = dayNumber,
                    textAlign = TextAlign.Center,
                    style = BlackoutTextStyle.h3SmallHeading.copy(color = White),
                    modifier = Modifier.padding(4.dp)
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScheduleHeaderPreview() {
    BlackoutUaTheme {
        ScheduleHeader(

        )
    }
}