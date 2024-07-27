package com.august.ua.blackout.presentation.create_update_location.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.room.util.TableInfo
import com.august.ua.blackout.R
import com.august.ua.blackout.data.dto.OutragePushTime
import com.august.ua.blackout.data.dvo.PushTimeDvo
import com.august.ua.blackout.presentation.common.DevicePreviews
import com.august.ua.blackout.presentation.common.extensions.blackoutRadialGradientBorder
import com.august.ua.blackout.ui.theme.BlackoutTextStyle
import com.august.ua.blackout.ui.theme.BlackoutUaTheme
import com.august.ua.blackout.ui.theme.White

@Composable
fun PushItem(
    modifier: Modifier,
    isPushOn: Boolean,
    onCheckedChange: (Boolean) -> Unit
) = Row(
    modifier = modifier
        .wrapContentHeight()
        .fillMaxWidth()
        .background(
            color = White,
            shape = MaterialTheme.shapes.medium
        )
        .padding(bottom = 16.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    Column(
        modifier = Modifier
            .weight(1f),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            text = stringResource(id = R.string.outrage_change_push_title),
            style = BlackoutTextStyle.h3SmallHeading
        )
    }

    Switch(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        checked = isPushOn,
        onCheckedChange = onCheckedChange,
    )
}

@Composable
fun PushItems(
    modifier: Modifier,
    pushTimes: List<PushTimeDvo>,
    isOutrageChangePushOn: Boolean,
    onOutrageChangePushChange: (Boolean) -> Unit,
    onCheckedChange: (OutragePushTime, Boolean) -> Unit
) = Column(
    modifier = modifier
        .wrapContentHeight()
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(
                color = White,
                shape = MaterialTheme.shapes.medium
            )
            .padding(bottom = 8.dp),
    ) {

        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp, bottom = 6.dp),
            text = stringResource(id = R.string.location_push_on_title),
            style = BlackoutTextStyle.h3SmallHeading
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {

            items(pushTimes) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .alpha(0.3f)
                        .blackoutRadialGradientBorder()
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .weight(1f),
                        text = stringResource(
                            id = R.string.location_push_on_description,
                            it.type.timeString
                        ),
                        style = BlackoutTextStyle.t3TextBody
                    )

                    Switch(
                        modifier = Modifier
                            .padding(end = 16.dp),
                        checked = it.isSelected,
                        onCheckedChange = { isOn ->
                            onCheckedChange(it.type, isOn)
                        },
                    )
                }
            }
        }
    }

    Text(
        modifier = Modifier
            .padding(top = 18.dp, bottom = 16.dp)
            .fillMaxWidth(),
        text = stringResource(id = R.string.outrage_change_push_description),
        style = BlackoutTextStyle.t2TextDescription
    )

    PushItem(
        modifier = Modifier
            .fillMaxWidth(),
        isPushOn = isOutrageChangePushOn,
        onCheckedChange = onOutrageChangePushChange
    )

}

//@DevicePreviews
//@Composable
//private fun PR() {
//    BlackoutUaTheme {
//        PushItems(
//            modifier = Modifier,
//            onCheckedChange = { _,_->
//
//            }
//        )
//    }
//}