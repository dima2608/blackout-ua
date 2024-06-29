package com.august.ua.blackout.presentation.onboarding.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.august.ua.blackout.presentation.common.DevicePreviews
import com.august.ua.blackout.ui.theme.BlackoutUaTheme
import com.august.ua.blackout.ui.theme.OnboardingButton
import com.august.ua.blackout.ui.theme.OnboardingProgress
import com.august.ua.blackout.ui.theme.OnboardingProgressTrack

@Composable
fun CompletionStepsProgressButton(
    modifier: Modifier = Modifier,
    currentStep: Int,
    totalSteps: Int,
    onClick: () -> Unit
) {
    val progressState = currentStep / totalSteps.toFloat()

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        CircularProgressIndicator(
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.Center),
            strokeWidth = 4.dp,
            color = OnboardingProgress,
            progress = progressState,
            trackColor = OnboardingProgressTrack,
            strokeCap =  ProgressIndicatorDefaults.CircularIndeterminateStrokeCap
        )

        Button(
            onClick = {
                onClick.invoke()
            },
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.Center),
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = OnboardingButton)
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "content description")
        }
    }
}


@DevicePreviews
@Composable
private fun CompletionProgressButtonPreview() {
    BlackoutUaTheme {
        CompletionStepsProgressButton(
            modifier = Modifier,
            4,
            5,
        ) {

        }
    }
}