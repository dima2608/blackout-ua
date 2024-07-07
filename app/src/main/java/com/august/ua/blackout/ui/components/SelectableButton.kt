package com.august.ua.blackout.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.august.ua.blackout.data.dvo.LocationColorType
import com.august.ua.blackout.data.dvo.LocationIconType
import com.august.ua.blackout.presentation.common.extensions.conditional
import com.august.ua.blackout.presentation.common.extensions.singleClick
import com.august.ua.blackout.ui.theme.Black
import com.august.ua.blackout.ui.theme.PeriwinkleGrayAlpha30
import com.august.ua.blackout.ui.theme.WhiteAlpha70

@Composable
fun SelectableButton(
    iconType: LocationIconType? = null,
    colorType: LocationColorType? = null,
    isSelected: Boolean,
    onClick: (icon: LocationIconType?, color: LocationColorType?) -> Unit
) {
    val shape = MaterialTheme.shapes.small
    Box(
        modifier = Modifier
            .padding(4.dp)
    ) {
        Box(
            modifier = Modifier
                .conditional(
                    condition = colorType != null,
                    modifier = {
                        Modifier.background(
                            color = colorType?.color!!,
                            shape = shape
                        )
                    }
                )
                .border(
                    width = 2.dp,
                    color = if (isSelected) Black else WhiteAlpha70,
                    shape = shape
                )
                .background(PeriwinkleGrayAlpha30, shape = shape)
                .clip(shape = shape)
                .singleClick {
                    onClick.invoke(iconType, colorType)
                },
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.Center
            ) {
                if (iconType != null) {
                    Icon(
                        modifier = Modifier
                            .padding(16.dp),
                        imageVector = iconType.icon,
                        contentDescription = null
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(45.dp)
                    )
                }
            }
        }
    }
}