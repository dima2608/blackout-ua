package com.august.ua.blackout.presentation.create_update_location.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.august.ua.blackout.R
import com.august.ua.blackout.ui.theme.BlackoutTextStyle

fun LazyListScope.itemTitle(
    @StringRes
    title: Int
) = item {
    Text(
        modifier = Modifier
            .padding(top = 18.dp, bottom = 16.dp)
            .fillMaxWidth(),
        text = stringResource(id = title),
        style = BlackoutTextStyle.t2TextDescription
    )
}