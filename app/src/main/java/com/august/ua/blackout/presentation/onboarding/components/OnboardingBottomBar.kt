package com.august.ua.blackout.presentation.onboarding.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingBottomBar(
    title: String? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
        ) {
            androidx.compose.animation.AnimatedVisibility(
                visible = title != null,
                enter = slideInHorizontally(initialOffsetX = { w -> w }) + expandHorizontally(
                    expandFrom = Alignment.End
                ),
                exit = slideOutHorizontally(targetOffsetX = { w -> w }) + shrinkHorizontally(
                    shrinkTowards = Alignment.End
                )
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = title!!,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
            }

        }

        CompletionStepsProgressButton(
            modifier = Modifier,
            4,
            5,
        ) {

        }

    }
}