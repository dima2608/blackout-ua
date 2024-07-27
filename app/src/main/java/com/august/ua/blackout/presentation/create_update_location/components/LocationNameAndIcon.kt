package com.august.ua.blackout.presentation.create_update_location.components

import androidx.annotation.StringRes
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
import androidx.compose.ui.unit.sp
import com.august.ua.blackout.R
import com.august.ua.blackout.data.dvo.LocationColorType
import com.august.ua.blackout.data.dvo.LocationIconType
import com.august.ua.blackout.presentation.common.DevicePreviews
import com.august.ua.blackout.ui.components.InputTextField
import com.august.ua.blackout.ui.components.NonLazyGrid
import com.august.ua.blackout.ui.components.SelectableButton
import com.august.ua.blackout.ui.theme.BlackoutTextStyle
import com.august.ua.blackout.ui.theme.BlackoutUaTheme
import com.august.ua.blackout.ui.theme.White

@Composable
fun LocationNameAndIcon(
    modifier: Modifier,
    name: String,
    onNameChanged: (String) -> Unit,
    selectedIcon: LocationIconType,
    selectedIconChanged: (LocationIconType) -> Unit,
    @StringRes
    nameError: Int?,
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
                .padding(top = 16.dp, bottom = 20.dp),
            text = stringResource(id = R.string.location_details),
            style = BlackoutTextStyle.h3SmallHeading.copy(fontSize = 14.sp)
        )


        InputTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp),
            placeholder = stringResource(id = R.string.location_name_label),
            value = name,
            isError = nameError != null,
            onValueChange = onNameChanged,
            supportingText = if (nameError!= null) stringResource(id = nameError) else null
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp, bottom = 20.dp),
            text = stringResource(id = R.string.icon),
            style = BlackoutTextStyle.h3SmallHeading.copy(fontSize = 14.sp)
        )

        NonLazyGrid(

            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .padding(bottom = 16.dp)
            ,
            columns = 6,
            itemCount = LocationIconType.entries.size
        ) {
            val item = LocationIconType.entries[it]
            SelectableButton(
                isSelected = selectedIcon == item,
                colorType = null,
                iconType = item,
                onClick = {icon,_ ->
                    if (icon != null) selectedIconChanged(icon)
                }
            )
        }
    }
}

fun LazyListScope.locationNameAndIcon(
    modifier: Modifier,
    name: String,
    onNameChanged: (String) -> Unit,
    selectedIcon: LocationIconType,
    selectedIconChanged: (LocationIconType) -> Unit,
    @StringRes
    nameError: Int?,
) = item {
    LocationNameAndIcon(
        modifier = modifier,
        name = name,
        onNameChanged = onNameChanged,
        selectedIcon = selectedIcon,
        selectedIconChanged = selectedIconChanged,
        nameError = nameError
    )
}

@DevicePreviews
@Composable
private fun Preview() {
    BlackoutUaTheme {
        LocationNameAndIcon(
            modifier = Modifier.fillMaxWidth(),
            name = "",
            onNameChanged = {

            },
            selectedIconChanged = {

            },
            selectedIcon = LocationIconType.Dumbbell,
            nameError = null
        )
    }
}