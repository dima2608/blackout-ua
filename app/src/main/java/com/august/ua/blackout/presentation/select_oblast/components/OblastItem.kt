package com.august.ua.blackout.presentation.select_oblast.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.august.ua.blackout.R
import com.august.ua.blackout.data.dto.Oblast
import com.august.ua.blackout.data.dvo.OblastDvo
import com.august.ua.blackout.presentation.common.DevicePreviews
import com.august.ua.blackout.presentation.common.extensions.singleClick
import com.august.ua.blackout.ui.theme.BlackoutTextStyle
import com.august.ua.blackout.ui.theme.BlackoutUaTheme
import com.august.ua.blackout.ui.theme.Gray

@Composable
fun OblastItem(
    oblast: Oblast,
    onClick: (oblast: Oblast) -> Unit
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
                painter = painterResource(id = oblast.icon),
                contentDescription = null
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = oblast.oblastName),
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


fun LazyListScope.oblastItem(
    oblast: Oblast,
    onClick: (oblast: Oblast) -> Unit
) = item { OblastItem(oblast, onClick) }


@DevicePreviews
@Composable
private fun OblastItemPreview() {
    BlackoutUaTheme {
        OblastItem(
            Oblast.Cherkasy

        ) {}
    }
}
