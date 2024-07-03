package com.august.ua.blackout.presentation.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.august.ua.blackout.R
import com.august.ua.blackout.presentation.common.NavigationEvent
import com.august.ua.blackout.ui.infrastructure.event.UIAction
import com.august.ua.blackout.ui.theme.BlackoutTextStyle
import com.august.ua.blackout.ui.utils.resource.UiText

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: SplashScreenViewModel = hiltViewModel(),
    navigate: (String) -> Unit
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_ukrain))
    val progress by animateLottieCompositionAsState(composition)
    val navState by viewModel.navEvent.collectAsState()
    val data = viewModel.uiData.value

    LaunchedEffect(navState) {
        when(val state =navState) {
            NavigationEvent.CloseScreen -> Unit
            is NavigationEvent.NavigateTo -> navigate(state.screenRoute)
            NavigationEvent.None -> Unit
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.bg_blue_yellow_gradient),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                modifier = modifier
                    .wrapContentSize()
                    .align(Alignment.Center),
                text = data.greetingText.asString(),
                style = BlackoutTextStyle.greetingText
            )


            LottieAnimation(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
                ,
                composition = composition,
                progress = progress,
                alignment = Alignment.BottomCenter
            )
            if (progress >= 1.0) {
                viewModel.onUIAction(UIAction(actionKey = data.animationActionKey))
            }
        }
    }
}

data class SplashScreenData(
    val greetingText: UiText,
    val animationActionKey: String
)