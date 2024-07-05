package com.august.ua.blackout.presentation.home.locations_tab.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Light
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.august.ua.blackout.data.dvo.ElectricityStatus
import com.august.ua.blackout.data.dvo.LocationDvo
import com.august.ua.blackout.presentation.common.DevicePreviews
import com.august.ua.blackout.presentation.common.extensions.singleClick
import com.august.ua.blackout.ui.theme.BlackoutTextStyle
import com.august.ua.blackout.ui.theme.BlackoutUaTheme
import com.august.ua.blackout.ui.theme.BlueAlpha37
import com.august.ua.blackout.ui.theme.White

@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun LocationExpandItem(
    modifier: Modifier = Modifier,
    location: LocationDvo,
    onClick: (LocationDvo) -> Unit,
    onLongClick: (LocationDvo) -> Unit
) {

    var isCollapsed by rememberSaveable(location.isCollapsedState) { mutableStateOf(location.isCollapsedState) }

    Box(
        modifier = modifier
            .wrapContentHeight()
            .background(color = Color.White, shape = MaterialTheme.shapes.small)
            .clip(shape = MaterialTheme.shapes.small)
            .drawBehind {
                // Calculate the start and end coordinates for the solid color
                val colorWidth = 80.dp.toPx() // Width of the solid color area in pixels
                val startX = 0f
                val endX = startX + colorWidth

                // Draw a rect with the solid color
                drawRect(
                    color = location.status.color, // Use the color from LocationDvo or any other color
                    topLeft = Offset(startX, 0f),
                    size = Size(colorWidth, size.height),
                    style = Fill
                )
            }
            .combinedClickable(
                onClick = {

                },
                onDoubleClick = {

                },
                onLongClick = {

                }
            )
            .singleClick {
                if (location.canBeCollapsed) {
                    isCollapsed = !isCollapsed
                }
            },
    ) {
        Box(
            modifier = Modifier
                .wrapContentHeight()
        ) {
            Row(
                modifier = Modifier
            ) {
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .align(Alignment.CenterVertically),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        imageVector = Icons.Outlined.Lightbulb,
                        contentDescription = null
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(color = White)
                        .padding(vertical = 24.dp, horizontal = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            modifier = Modifier
                                .padding(end = 16.dp),
                            imageVector = location.icon,
                            contentDescription = null
                        )

                        Text(
                            modifier = Modifier,
                            text = location.locationName,
                            style = BlackoutTextStyle.h2MediumHeading,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .padding(vertical = 8.dp),
                        text = location.queueAndLocation,
                        style = BlackoutTextStyle.t1BigText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    AnimatedVisibility(
                        modifier = Modifier
                            .background(White)
                            .wrapContentHeight(),
                        visible = isCollapsed.not()
                    ) {
                        Column {

                            Text(
                                modifier = Modifier
                                    .padding(vertical = 8.dp),
                                text = location.status.name,
                                style = BlackoutTextStyle.t1BigText,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                modifier = Modifier
                                    .padding(vertical = 8.dp),
                                text = location.period,
                                style = BlackoutTextStyle.t1BigText,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Text(
                                modifier = Modifier
                                    .padding(vertical = 8.dp),
                                text = location.lightTurnOnIn,
                                style = BlackoutTextStyle.t1BigText,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }


                if (location.canBeCollapsed) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .width(60.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier,
                            imageVector = if (isCollapsed) Icons.Outlined.KeyboardArrowDown else Icons.Outlined.KeyboardArrowUp,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}


@DevicePreviews
@Composable
private fun LocationExpandItemPreview() {
    BlackoutUaTheme {
        Column(modifier = Modifier.fillMaxSize().background(BlueAlpha37)) {
            LocationExpandItem(
                modifier = Modifier.padding(bottom = 16.dp),
                location = LocationDvo(
                    status = ElectricityStatus.Available,
                    period = "Період:  20.00 - 21.00",
                    locationName = "Мій дім",
                    lightTurnOnIn = "Включення через:   59 хв.",
                    icon = Icons.Default.Home,
                    isCollapsedState = false,
                    queueAndLocation = "1 черга (Черкаська обл.)",
                    canBeCollapsed = false
                ),
                onClick = {

                },
                onLongClick = {

                }
            )


            LocationExpandItem(
                location = LocationDvo(
                    status = ElectricityStatus.Unavailable,
                    period = "Період:  20.00 - 21.00",
                    locationName = "Мій дім",
                    lightTurnOnIn = "Включення через:   59 хв.",
                    icon = Icons.Default.Light,
                    isCollapsedState = true,
                    queueAndLocation = "1 черга (Черкаська обл.)",
                    canBeCollapsed = true
                ),
                onClick = {

                },
                onLongClick = {

                }
            )
        }
    }
}