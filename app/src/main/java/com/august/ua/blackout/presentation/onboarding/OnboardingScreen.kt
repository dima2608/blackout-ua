package com.august.ua.blackout.presentation.onboarding

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun OnboardingScreen(
    viewModel: OnboardingScreenViewModel = hiltViewModel(),
) {
    OnboardingScreenContent(

    )
}

@Composable
private fun OnboardingScreenContent(

) {

    val snackbarHostState = SnackbarHostState()

    Scaffold(
        snackbarHost = {

        }
    ) { paddingValues ->


    }
}