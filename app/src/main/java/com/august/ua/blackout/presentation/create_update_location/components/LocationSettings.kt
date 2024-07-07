package com.august.ua.blackout.presentation.create_update_location.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.august.ua.blackout.R
import com.august.ua.blackout.data.dvo.LocationColorType
import com.august.ua.blackout.data.dvo.LocationIconType
import com.august.ua.blackout.presentation.common.DevicePreviews
import com.august.ua.blackout.ui.theme.BlackoutUaTheme

@Composable
fun LocationSettings(
    modifier: Modifier,
    name: String,
    selectedIcon: LocationIconType,
    selectedColor: LocationColorType,
    onNameChanged: (String) -> Unit,
    selectedIconChanged: (LocationIconType) -> Unit,
    selectedColorChanged: (LocationColorType) -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = modifier,
        state = listState
    ) {
        locationNameAndIcon(
            modifier = Modifier
                .fillMaxWidth(),
            name = name,
            onNameChanged = onNameChanged,
            selectedIcon = selectedIcon,
            selectedIconChanged = selectedIconChanged,

        )

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }

        itemTitle(title = R.string.location_color_description_second)

        colorItem(
            modifier = Modifier
                .fillMaxWidth(),
            selectedColor = selectedColor,
            selectedColorChanged = selectedColorChanged
        )
    }
}

@DevicePreviews
@Composable
private fun Preview() {
    BlackoutUaTheme {
        LocationSettings(
            modifier =  Modifier
                .fillMaxWidth(),
            name = "",
            selectedIcon = LocationIconType.Location,
            selectedColor = LocationColorType.Red,
            onNameChanged = {

            },
            selectedIconChanged = {

            },
            selectedColorChanged = {

            }
        )
    }
}