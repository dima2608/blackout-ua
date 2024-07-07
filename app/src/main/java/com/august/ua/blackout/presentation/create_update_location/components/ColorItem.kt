package com.august.ua.blackout.presentation.create_update_location.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.august.ua.blackout.R
import com.august.ua.blackout.data.dvo.LocationColorType
import com.august.ua.blackout.ui.components.NonLazyGrid
import com.august.ua.blackout.ui.components.SelectableButton
import com.august.ua.blackout.ui.theme.BlackoutTextStyle
import com.august.ua.blackout.ui.theme.White

fun LazyListScope.colorItem(
    modifier: Modifier,
    selectedColor: LocationColorType,
    selectedColorChanged: (LocationColorType) -> Unit
) = item {

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
                .padding(top = 16.dp, bottom = 20.dp),
            text = stringResource(id = R.string.icon),
            style = BlackoutTextStyle.h5SmallestHeading
        )

        NonLazyGrid(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .padding(bottom = 16.dp),
            columns = 6,
            itemCount = LocationColorType.entries.size
        ) {
            val item = LocationColorType.entries[it]
            SelectableButton(
                isSelected = selectedColor == item,
                colorType = LocationColorType.entries[it],
                iconType = null,
                onClick = { _, color ->
                    if (color != null) selectedColorChanged.invoke(color)
                }
            )
        }
    }
}