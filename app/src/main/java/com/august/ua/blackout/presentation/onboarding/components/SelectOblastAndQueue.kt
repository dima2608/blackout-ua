package com.august.ua.blackout.presentation.onboarding.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.buildSpannedString
import com.august.ua.blackout.R
import com.august.ua.blackout.ui.theme.Black
import com.august.ua.blackout.ui.theme.BlackoutTextStyle
import com.august.ua.blackout.ui.theme.Primary

const val OBLAST_TEG = "oblast_teg"
const val QUEUE_TAG = "queue_tag"

@Composable
fun SelectOblastAndQueue(
    @StringRes
    title: Int,
    @DrawableRes
    icon: Int,
    @StringRes
    description: Int,
    selectedOblast: String? = null,
    selectedQueue: String? = null
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp

    val imageHeight = screenHeight - (screenHeight - 545)

    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(imageHeight.dp),
        painter = painterResource(id = icon),
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
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 16.dp),
                style = BlackoutTextStyle.t3TextBody.copy(fontSize = 14.sp),
                textAlign = TextAlign.Center,
            )

            val annotatedString = buildAnnotatedString {
                val chooseOblastString = "${stringResource(id = R.string.choose_your_oblast)}: "
                val chooseQueueString = "${stringResource(id = R.string.choose_your_queue)}: "

                val clickHereToSelectString = stringResource(id = R.string.click_here_to_select)

                append(chooseOblastString)

                pushStringAnnotation(tag = OBLAST_TEG, annotation = "https://oblast")

                withStyle(
                    style = SpanStyle(
                        color = Black,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append(clickHereToSelectString)
                }
                pop()

                append("\n\n")

                append(chooseQueueString)

                pushStringAnnotation(tag = QUEUE_TAG, annotation = "https://queue")

                withStyle(
                    style = SpanStyle(
                        color = Black,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append(clickHereToSelectString)
                }
                pop()
            }

            ClickableText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                text = annotatedString, style = BlackoutTextStyle.t3TextBody.copy(fontSize = 14.sp, textAlign = TextAlign.Start),
                onClick = { offset ->
                    annotatedString.getStringAnnotations(
                        tag = OBLAST_TEG,
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let {
                        //Log.d("policy URL", it.item)
                    }

                    annotatedString.getStringAnnotations(
                        tag = QUEUE_TAG,
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let {
                        //Log.d("terms URL", it.item)
                    }
                }
            )


        }
    }
}

