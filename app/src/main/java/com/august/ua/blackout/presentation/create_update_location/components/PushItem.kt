package com.august.ua.blackout.presentation.create_update_location.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = White,
                shape = MaterialTheme.shapes.medium
            )
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            text = stringResource(id = R.string.location_push_on_title),
            style = BlackoutTextStyle.h5SmallestHeading
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            text = stringResource(id = R.string.location_push_on_description),
            style = BlackoutTextStyle.t3TextBody
        )
    }

    Switch(
        checked = isPushOn,
        onCheckedChange = onCheckedChange,
    )
}