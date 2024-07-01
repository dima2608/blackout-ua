package com.august.ua.blackout.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    // TODO: colors for DarkTheme are not configured
    primary = Color(0xFFFCE000),
    onPrimary = Color(0xFF1F1F1F),
    primaryContainer = Color(0xFFFEF9CC),
    onPrimaryContainer = Color(0xFF434343),

    secondary = Color(0xFF1F1F1F),
    onSecondary = Color(0xFFFAFAFA),
    secondaryContainer = Color(0xFFF0F0F0),
    onSecondaryContainer = Color(0xFF1F1F1F),

    error = Color(0xFFFF012F),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFF9DEDC),
    onErrorContainer = Color(0xFF40000C),

    surface = Color(0xFFFFFFFF),
    surfaceContainer = Color(0xFFF5F5F5),
    onSurfaceVariant = Color(0xFF595959),
    onSurface = Color(0xFF1F1F1F),
    inverseSurface = Color(0xFF1F1F1F),
    inverseOnSurface = Color(0xFFD6D6D6),

    outline = Color(0xFFD9D9D9),
    outlineVariant = Color(0xFFD4D4D4),

    tertiary = Color(0xFFA098FF),
    background = Color.Transparent,

    onBackground = Color(0xFF1C1B1F),
)

private val LightColorScheme = lightColorScheme(
    primary = Black,
    onPrimary = White,
    primaryContainer = Gray,
    onPrimaryContainer = White,

    secondary = Color(0xFF1F1F1F),
    onSecondary = Color(0xFFFAFAFA),
    secondaryContainer = Color(0xFFF0F0F0),
    onSecondaryContainer = Color(0xFF1F1F1F),

    error = RedIndicator,
    onError = White,
    errorContainer = RedAttention,
    onErrorContainer = White,

    surface = Color(0xFFFFFFFF),
    surfaceContainer = Color(0xFFF5F5F5),
    onSurfaceVariant = Color(0xFF595959),
    onSurface = Color(0xFF1F1F1F),
    inverseSurface = Color(0xFF1F1F1F),
    inverseOnSurface = Color(0xFFD6D6D6),

    outline = Color(0xFFD9D9D9),
    outlineVariant = Color(0xFFD4D4D4),

    tertiary = Color(0xFFA098FF),
    background = Transparent,

    onBackground = Color(0xFF1C1B1F),
)

@Composable
fun BlackoutUaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        shapes = Shapes,
        content = content
    )
}