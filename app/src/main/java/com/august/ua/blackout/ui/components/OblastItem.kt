package com.august.ua.blackout.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.august.ua.blackout.data.dto.OblastType
import com.august.ua.blackout.data.dvo.OblastDvo
import com.august.ua.blackout.presentation.common.extensions.singleClick
import com.august.ua.blackout.ui.theme.BlackoutTextStyle
import com.august.ua.blackout.ui.theme.Gray

@Composable
fun OblastItem(
    oblast: OblastDvo,
    onClick: (oblast: OblastDvo) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .singleClick { onClick(oblast) },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.padding(horizontal = 16.dp),
                painter = painterResource(id = oblast.oblastType?.icon ?: OblastType.Unknown.icon),
                contentDescription = null
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = oblast.oblastType?.oblastName ?: OblastType.Unknown.oblastName),
                style = BlackoutTextStyle.t3TextBody
            )
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .height(1.dp)
            .background(Gray)
        )
    }
}
