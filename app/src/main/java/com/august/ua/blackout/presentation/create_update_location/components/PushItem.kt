package com.august.ua.blackout.presentation.create_update_location.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.august.ua.blackout.R
import com.august.ua.blackout.ui.theme.BlackoutTextStyle
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
            .weight(1f)
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            text = stringResource(id = R.string.location_push_on_title),
            style = BlackoutTextStyle.h3SmallHeading
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            text = stringResource(id = R.string.location_push_on_description),
            style = BlackoutTextStyle.t3TextBody
        )
    }

    Switch(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        checked = isPushOn,
        onCheckedChange = onCheckedChange,
    )
}