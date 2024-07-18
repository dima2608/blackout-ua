package com.august.ua.blackout.ui.components

import androidx.appcompat.widget.Toolbar
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.MenuOpen
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.august.ua.blackout.R
import com.august.ua.blackout.presentation.common.DevicePreviews
import com.august.ua.blackout.presentation.common.extensions.conditional
import com.august.ua.blackout.presentation.common.extensions.singleClick
import com.august.ua.blackout.ui.theme.BlackoutTextStyle
import com.august.ua.blackout.ui.theme.BlackoutUaTheme
import com.august.ua.blackout.ui.theme.BlueAlpha37
import com.august.ua.blackout.ui.theme.EggshellAlpha80
import com.august.ua.blackout.ui.theme.PeriwinkleGray
import com.august.ua.blackout.ui.theme.Primary
import com.august.ua.blackout.ui.theme.Transparent
import com.august.ua.blackout.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingToolbar(
    isBackButtonVisible: Boolean,
    showProgressIndicator: Boolean = false,
    onClick: () -> Unit
) {
    val buttonVisible by remember(isBackButtonVisible) {
        mutableStateOf(isBackButtonVisible)
    }

    Box {
        TopAppBar(
            title = {

            },
            navigationIcon = {
                AnimatedVisibility(visible = buttonVisible) {
                    IconButton(onClick = onClick) {
                        Image(
                            modifier = Modifier.padding(8.dp),
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = null
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                actionIconContentColor = Color.Unspecified,
                containerColor = Color.Transparent
            ),
            modifier = Modifier.background(Color.Transparent)
        )
        if (showProgressIndicator) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .conditional(
                        condition = !isBackButtonVisible,
                        modifier = {
                            Modifier
                                .statusBarsPadding()
                                .align(Alignment.TopCenter)
                        }
                    )
                    .conditional(
                        condition = isBackButtonVisible,
                        modifier = {
                            Modifier.align(Alignment.BottomCenter)
                        }
                    )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationToolbar(
    transparency: Float,
    showProgressIndicator: Boolean = false,
) {
    Box {
        TopAppBar(
            title = {
                    Image(
                        modifier = Modifier
                            .padding(start = 12.dp),
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        alpha = 1 - transparency
                    )

            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                navigationIconContentColor = Color.Unspecified,
                actionIconContentColor = Color.Unspecified,
                containerColor = Transparent
            )
        )
        if (showProgressIndicator) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateUpdateLocationToolbar(
    title: String,
    onBack: () -> Unit,
    showProgressIndicator: Boolean = false,
) {
    Box {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = { onBack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            },
            title = {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = title,
                    style = BlackoutTextStyle.t3TextBody
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                navigationIconContentColor = Color.Unspecified,
                actionIconContentColor = Color.Unspecified,
                containerColor = Transparent
            ),
        )
        if (showProgressIndicator) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarToolbar(
    onMenu: () -> Unit,
) {
    Box {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = { onMenu() }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null
                    )
                }
            },
            title = {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = "",
                    style = BlackoutTextStyle.t3TextBody
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                navigationIconContentColor = Color.Unspecified,
                actionIconContentColor = Color.Unspecified,
                containerColor = EggshellAlpha80
            )
        )
    }
}

@DevicePreviews
@Composable
private fun OnboardingToolbarPrev() {
    BlackoutUaTheme {
        OnboardingToolbar(true) {}
    }
}