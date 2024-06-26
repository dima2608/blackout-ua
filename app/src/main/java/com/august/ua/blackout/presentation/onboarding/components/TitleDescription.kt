package com.august.ua.blackout.presentation.onboarding.components

import android.content.ClipDescription
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingTitleDescription(
    title: String,
    description: String,
    isSkipPresent: Boolean,
    buttonTitle: String,
    onboardingStatePositionSize: Int,
    currentOnboardingStatePosition: Int,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {

        }
    }
}