package com.august.ua.blackout.presentation.create_update_location.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.august.ua.blackout.R
import com.august.ua.blackout.data.dto.OblastType
import com.august.ua.blackout.data.dvo.CityDvo
import com.august.ua.blackout.data.dvo.QueueDvo
import com.august.ua.blackout.presentation.common.DevicePreviews
import com.august.ua.blackout.presentation.common.extensions.blackoutRadialGradientBorder
import com.august.ua.blackout.ui.theme.BlackoutTextStyle
import com.august.ua.blackout.ui.theme.BlackoutUaTheme
import com.august.ua.blackout.ui.theme.White

@Composable
fun SelectQueue(
    modifier: Modifier,
    cities: CityDvo,
    onClick: (String) -> Unit
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp

    val maxHeight = screenHeight - (screenHeight - 576)
    val listState = rememberLazyListState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = White,
                shape = MaterialTheme.shapes.medium
            )
            .heightIn(max = maxHeight.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp, bottom = 20.dp),
            text = stringResource(id = R.string.your_region_title),
            style = BlackoutTextStyle.h3SmallHeading.copy(fontSize = 14.sp)
        )

        LazyColumn(
            modifier = Modifier
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            state = listState
        ) {
            items(cities.queues) { queue ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = queue.isSelected,
                            onClick = {
                                onClick(queue.queueName)
                            },
                            role = Role.RadioButton
                        )
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .height(1.dp)
                            .alpha(0.3f)
                            .blackoutRadialGradientBorder()

                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .semantics {
                                    contentDescription = "Localized Description"
                                },
                            selected = queue.isSelected,
                            onClick = null,
                        )

                        Text(
                            text = "${queue.queueName} " + stringResource(id = R.string.queue),
                            style = BlackoutTextStyle.t3TextBody
                        )
                    }
                }
            }
        }
    }
}