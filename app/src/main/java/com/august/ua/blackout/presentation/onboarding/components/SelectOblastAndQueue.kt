package com.august.ua.blackout.presentation.onboarding.components

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.buildSpannedString
import com.august.ua.blackout.R
import com.august.ua.blackout.presentation.common.extensions.singleClick
import com.august.ua.blackout.ui.components.InputTextField
import com.august.ua.blackout.ui.theme.Black
import com.august.ua.blackout.ui.theme.BlackoutTextStyle
import com.august.ua.blackout.ui.theme.Primary
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

@Composable
fun SelectOblastAndQueue(
    @StringRes
    title: Int,
    @DrawableRes
    icon: Int,
    @StringRes
    description: Int,
    selectedOblast: String? = null,
    selectedQueue: String? = null,
    @StringRes
    oblastError: Int? = null,
    @StringRes
    queueError: Int? = null,
    onOblastClick: () -> Unit,
    onQueueClick: () -> Unit,
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp

    val imageHeight = screenHeight - (screenHeight - 545)

    val interactionOblastSource = remember {
        object : MutableInteractionSource {
            override val interactions = MutableSharedFlow<Interaction>(
                extraBufferCapacity = 16,
                onBufferOverflow = BufferOverflow.DROP_OLDEST,
            )

            override suspend fun emit(interaction: Interaction) {
                if (interaction is PressInteraction.Release) {
                    onOblastClick()
                }

                interactions.emit(interaction)
            }

            override fun tryEmit(interaction: Interaction): Boolean {
                return interactions.tryEmit(interaction)
            }
        }
    }

    val interactionQueueSource = remember {
        object : MutableInteractionSource {
            override val interactions = MutableSharedFlow<Interaction>(
                extraBufferCapacity = 16,
                onBufferOverflow = BufferOverflow.DROP_OLDEST,
            )

            override suspend fun emit(interaction: Interaction) {
                if (interaction is PressInteraction.Release) {
                    onQueueClick()
                }

                interactions.emit(interaction)
            }

            override fun tryEmit(interaction: Interaction): Boolean {
                return interactions.tryEmit(interaction)
            }
        }
    }

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
                    .padding(horizontal = 24.dp)
                    .padding(top = 32.dp, bottom = 16.dp),
                style = BlackoutTextStyle.h1Heading,
                textAlign = TextAlign.Center,
            )

            InputTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 24.dp),
                value = selectedOblast.orEmpty(),
                onValueChange = {},
                placeholder = stringResource(id = R.string.choose_your_oblast),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = oblastError != null,
                supportingText = if (oblastError != null) stringResource(id = oblastError) else null,
                readOnly = true,
                interactionSource = interactionOblastSource
            )

            val queueValue = if (selectedQueue.isNullOrBlank()) ""
            else "$selectedOblast ${stringResource(id = R.string.queue)}"

            InputTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 24.dp),
                value = queueValue,
                onValueChange = {},
                placeholder = stringResource(id = R.string.choose_your_queue),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = queueError != null,
                supportingText = if (queueError != null) stringResource(id = queueError) else null,
                readOnly = true,
                interactionSource = interactionQueueSource
            )
        }
    }
}

