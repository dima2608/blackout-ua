package com.august.ua.blackout.presentation.common.extensions

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.august.ua.blackout.presentation.common.single_click.MultipleEventsCutter
import com.august.ua.blackout.presentation.common.single_click.get
import com.august.ua.blackout.ui.theme.GradientAlternativePosition01
import com.august.ua.blackout.ui.theme.GradientAlternativePosition02
import com.august.ua.blackout.ui.theme.GradientAlternativePosition03
import com.august.ua.blackout.ui.theme.GradientAlternativePosition04
import com.august.ua.blackout.ui.theme.GradientAlternativePosition05
import com.august.ua.blackout.ui.theme.GradientAlternativePosition06
import com.august.ua.blackout.ui.theme.GradientAlternativePosition1
import com.august.ua.blackout.ui.theme.GradientAlternativePosition2
import com.august.ua.blackout.ui.theme.GradientAlternativePosition3
import com.august.ua.blackout.ui.theme.GradientAlternativePosition4
import com.august.ua.blackout.ui.theme.PeriwinkleGray
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

fun Modifier.conditional(condition: Boolean, modifier: Modifier.() -> Modifier): Modifier {
    return if (condition) then(modifier(Modifier)) else this
}

@SuppressLint("RememberReturnType", "ModifierFactoryUnreferencedReceiver")
@Composable
fun Modifier.singleClick(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    indication:  Indication? = LocalIndication.current,
    delay: Long = 300L,
    onClick: () -> Unit,
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "clickable"
        properties["enabled"] = enabled
        properties["indication"] =  indication
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    val multipleEventsCutter = remember { MultipleEventsCutter.get() }
    Modifier.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = { multipleEventsCutter.processEvent(event = { onClick() }, delay = delay) },
        role = role,
        indication = indication,
        interactionSource = remember { MutableInteractionSource() }
    )
}


fun Modifier.scaleOnPress(
    interactionSource: InteractionSource
) = composed {
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        if (isPressed) {
            1.95f
        } else {
            1f
        }, label = "scale"
    )
    this.graphicsLayer {
        scaleX = scale
        scaleY = scale
    }
    this.indication(
        interactionSource = remember { MutableInteractionSource() },
        indication = null
    )
}

fun Modifier.gradientBackground(colors: List<Color>, angle: Float) = this.then(
    Modifier.drawBehind {
        val angleRad = angle / 180f * PI
        val x = cos(angleRad).toFloat() //Fractional x
        val y = sin(angleRad).toFloat() //Fractional y

        val radius = sqrt(size.width.pow(2) + size.height.pow(2)) / 2f
        val offset = center + Offset(x * radius, y * radius)

        val exactOffset = Offset(
            x = min(offset.x.coerceAtLeast(0f), size.width),
            y = size.height - min(offset.y.coerceAtLeast(0f), size.height)
        )

        drawRect(
            brush = Brush.linearGradient(
                colors = colors,
                start = Offset(size.width, size.height) - exactOffset,
                end = exactOffset
            ),
            size = size
        )
    }
)

@SuppressLint("UnnecessaryComposedModifier", "ModifierFactoryUnreferencedReceiver")
fun Modifier.blackoutLinearGradientBorder() =
    composed {
        border(
            2.dp, brush = Brush.linearGradient(
                colors = listOf(
                    GradientAlternativePosition1,
                    GradientAlternativePosition2,
                    GradientAlternativePosition3,
                    GradientAlternativePosition4,
                    PeriwinkleGray,
                )
            ), RoundedCornerShape(8.dp)
        )
    }

private const val RADIUS_MULTIPLIER = 3.0f

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.blackoutRadialGradientBorder() =
    composed {

        val configuration = LocalConfiguration.current

        val screenWidth = configuration.screenWidthDp.dp.value

        border(
            2.dp, brush = Brush.radialGradient(
//                center = Offset.Zero,
                center = Offset(0.0f, 100.0f),
                radius = screenWidth * RADIUS_MULTIPLIER,
                colors = listOf(
                    GradientAlternativePosition01,
                    GradientAlternativePosition02,
                    GradientAlternativePosition03,
                    GradientAlternativePosition04,
                    GradientAlternativePosition05,
                    GradientAlternativePosition06,
                ),
            ), RoundedCornerShape(8.dp)
        )
    }
