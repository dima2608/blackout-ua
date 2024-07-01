package com.august.ua.blackout.presentation.onboarding.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.august.ua.blackout.ui.theme.BlackoutTextStyle
import com.gigamole.composeshadowsplus.common.shadowsPlus

@Composable
fun ImageAndDescription(
    @DrawableRes
    image: Int,
    @StringRes
    title: Int,
    @StringRes
    description: Int
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp

    val imageHeight = screenHeight - (screenHeight - 545)

    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(imageHeight.dp),
        painter = painterResource(id = image),
        contentDescription = null,
        contentScale = ContentScale.None
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
            .background(MaterialTheme.colorScheme.surface)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = stringResource(id = title),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                style = BlackoutTextStyle.h1Heading,
                textAlign = TextAlign.Center,
            )

            Text(
                text = stringResource(id = description),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                style = BlackoutTextStyle.t3TextBody.copy(fontSize = 14.sp),
                textAlign = TextAlign.Center,
            )
        }
    }
}