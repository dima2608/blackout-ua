package com.august.ua.blackout.presentation.create_update_location

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.webkit.URLUtil
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.august.ua.blackout.R
import com.august.ua.blackout.data.dto.OblastType
import com.august.ua.blackout.data.dvo.CityDvo
import com.august.ua.blackout.data.dvo.QueueDvo
import com.august.ua.blackout.presentation.common.DevicePreviews
import com.august.ua.blackout.presentation.common.NavigationEvent
import com.august.ua.blackout.presentation.common.ScreenState
import com.august.ua.blackout.presentation.common.extensions.singleClick
import com.august.ua.blackout.presentation.create_update_location.components.LocationSettings
import com.august.ua.blackout.presentation.create_update_location.components.PushItems
import com.august.ua.blackout.presentation.create_update_location.components.SelectQueue
import com.august.ua.blackout.presentation.create_update_location.components.SelectRegion
import com.august.ua.blackout.presentation.create_update_location.event.CreateUpdateLocationEvent
import com.august.ua.blackout.presentation.create_update_location.state.CreateUpdateLocationState
import com.august.ua.blackout.ui.components.AppButton
import com.august.ua.blackout.ui.components.AppSnackBar
import com.august.ua.blackout.ui.components.CreateUpdateLocationToolbar
import com.august.ua.blackout.ui.components.showSnackbar
import com.august.ua.blackout.ui.theme.BlackoutTextStyle
import com.august.ua.blackout.ui.theme.BlackoutUaTheme
import com.google.common.reflect.Reflection.getPackageName


@Composable
fun CreateUpdateLocationScreen(
    viewModel: CreateUpdateLocationViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    navigate: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val screenState by viewModel.screenState.collectAsState()
    val navEvent by viewModel.navEvent.collectAsState()


    CreateUpdateLocationContent(
        uiState = uiState,
        onUiEvent = viewModel::onUiEvent,
        screenState = screenState
    )

    BackHandler {
        viewModel.onUiEvent(CreateUpdateLocationEvent.PreviousScreen)
    }

    LaunchedEffect(navEvent) {
        when (val state = navEvent) {
            NavigationEvent.CloseScreen -> navigateBack()
            is NavigationEvent.NavigateTo -> navigate(state.screenRoute)
            NavigationEvent.None -> Unit
        }
    }
}

@Composable
private fun CreateUpdateLocationContent(
    uiState: CreateUpdateLocationState,
    onUiEvent: (CreateUpdateLocationEvent) -> Unit,
    screenState: ScreenState
) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val snackbar = remember {
        SnackbarHostState()
    }

    var link by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier
            .singleClick(indication = null) {
                focusManager.clearFocus()
            },
        topBar = {
            CreateUpdateLocationToolbar(
                title = stringResource(id = uiState.toolbarTitle ?: R.string.empty_string_space),
                showProgressIndicator = screenState == ScreenState.Loading,
                onBack = {
                    onUiEvent(CreateUpdateLocationEvent.PreviousScreen)
                }
            )
        },
        snackbarHost = {
            AppSnackBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 12.dp),
                snackBarHostState = snackbar
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.bg_wite_blue_purpule),
                    contentScale = ContentScale.FillBounds
                )
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),
                        text = stringResource(id = uiState.headline ?: R.string.empty_string_space),
                        style = BlackoutTextStyle.h1Heading.copy(fontWeight = FontWeight.W700)
                    )

                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 18.dp, bottom = 16.dp)
                            .fillMaxWidth(),
                        text = stringResource(id = uiState.subtitle ?: R.string.empty_string_space),
                        style = BlackoutTextStyle.t2TextDescription
                    )

                    when (uiState) {
                        CreateUpdateLocationState.Initial -> Unit
                        is CreateUpdateLocationState.LocationState -> {
                            SelectRegion(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                listCities = uiState.listCities,
                                onClick = {
                                    onUiEvent(CreateUpdateLocationEvent.CityChanged(it))
                                }
                            )
                        }

                        is CreateUpdateLocationState.LocationQueueState -> {
                            link = uiState.link
                            SelectQueue(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                cities = uiState.selectedCity,
                                onClick = {
                                    onUiEvent(CreateUpdateLocationEvent.QueueChanged(it))
                                }
                            )
                        }

                        is CreateUpdateLocationState.LocationSettingsState -> {
                            LocationSettings(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                name = uiState.locationName,
                                selectedIcon = uiState.selectedIconType,
                                selectedColor = uiState.selectedColorType,
                                nameError = uiState.locationNameError,
                                onNameChanged = {
                                    onUiEvent(CreateUpdateLocationEvent.NameChanged(it))
                                },
                                selectedIconChanged = {
                                    onUiEvent(CreateUpdateLocationEvent.IconChanged(it))
                                },
                                selectedColorChanged = {
                                    onUiEvent(CreateUpdateLocationEvent.ColorChanged(it))
                                }
                            )
                        }

                        is CreateUpdateLocationState.LocationPushState -> {
                            PushItems(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                pushTimes = uiState.pushTimes,
                                isOutrageChangePushOn = uiState.isOutragePushOn,
                                onCheckedChange = { type, isOn ->
                                    onUiEvent(CreateUpdateLocationEvent.OnPushOn(type, isOn))
                                },
                                onOutrageChangePushChange = {
                                    onUiEvent(CreateUpdateLocationEvent.OnOutrageUpdatePushOn(it))
                                }
                            )
                        }
                    }
                }

                FindYourQueue(link = link)

                AnimatedVisibility(uiState != CreateUpdateLocationState.Initial) {
                    AppButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        text = stringResource(
                            id = uiState.buttonTitle ?: R.string.empty_string_space
                        ),
                    ) {
                        onUiEvent(CreateUpdateLocationEvent.NextScreen)
                    }
                }
            }
        }

        val noInternetConnectionTitle = stringResource(id = R.string.no_internet_connection)
        val noInternetConnectionLabel = stringResource(id = R.string.retry)

        val activity = LocalContext.current as Activity

        LaunchedEffect(screenState) {
            when (screenState) {
                is ScreenState.ErrorState -> {
                    if (screenState.customRetryTitle != null) {
                        showSnackbar(
                            message = screenState.error,
                            actionLabel = screenState.customRetryTitle,
                            scope = scope,
                            snackbar = snackbar,
                            onSnackbarDismissed = {
                                onUiEvent(CreateUpdateLocationEvent.OnSnackbarDismissed)
                            },
                            onActionPerformed = {
                                val intent = Intent()
                                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS")
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                intent.putExtra(
                                    "android.provider.extra.APP_PACKAGE",
                                    activity.packageName
                                )
                                activity.startActivity(intent)

                                onUiEvent(CreateUpdateLocationEvent.ResetScreenState)
                            }
                        )
                    } else {
                        showSnackbar(
                            message = screenState.error,
                            scope = scope,
                            snackbar = snackbar,
                            onSnackbarDismissed = {
                                onUiEvent(CreateUpdateLocationEvent.OnSnackbarDismissed)
                            }
                        )
                    }
                }

                ScreenState.Loading -> {}
                ScreenState.NoInternetConnection -> showSnackbar(
                    message = noInternetConnectionTitle,
                    actionLabel = noInternetConnectionLabel,
                    scope = scope,
                    snackbar = snackbar,
                    onSnackbarDismissed = {
                        onUiEvent(CreateUpdateLocationEvent.OnSnackbarDismissed)
                    },
                    onActionPerformed = {
                        onUiEvent(CreateUpdateLocationEvent.OnSnackbarRetry)
                    }
                )

                ScreenState.NoInternetConnectionFullscreen -> Unit
                ScreenState.None -> {
                    snackbar.currentSnackbarData?.dismiss()
                }
            }
        }

    }
}

@Composable
private fun FindYourQueue(
    link: String
) {
    val uriHandler = LocalUriHandler.current

    AnimatedVisibility(
        visible = link.isNullOrBlank().not()
    ) {
        val buildAnnotatedString = buildAnnotatedString {
            append(stringResource(id = R.string.you_do_not_now_your_queue))
            append("\n")
            append(stringResource(id = R.string.go_to))
            append(" ")

            pushStringAnnotation(tag = "link_to", annotation = link)
            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                append(stringResource(id = R.string.your_region_list_link))
            }
            pop()
        }

        ClickableText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            text = buildAnnotatedString,
            style = BlackoutTextStyle.t4TextSmallDescription,
            onClick = { offset ->
                buildAnnotatedString.getStringAnnotations(
                    tag = "link_to",
                    start = offset,
                    end = offset
                ).firstOrNull()?.let {
                    if (URLUtil.isValidUrl(it.item)) {
                        uriHandler.openUri(it.item)
                    }
                }
            }
        )
    }
}

@DevicePreviews
@Composable
private fun Prev() {
    BlackoutUaTheme {
        CreateUpdateLocationContent(
            uiState = CreateUpdateLocationState.LocationState(
                R.string.create_location,
                R.string.your_queue_title,
                listOf(
                    CityDvo(
                        id = 1,
                        oblastType = OblastType.Odesa,
                        isSelected = false,
                        queues = listOf(
                            QueueDvo(
                                queueName = "1",
                                isSelected = false
                            ),
                            QueueDvo(
                                queueName = "2",
                                isSelected = false
                            ),
                            QueueDvo(
                                queueName = "3",
                                isSelected = false
                            )
                        )
                    ),
                    CityDvo(
                        id = 3,
                        oblastType = OblastType.Kyiv,
                        isSelected = false,
                        queues = listOf(
                            QueueDvo(
                                queueName = "1",
                                isSelected = false
                            ),
                            QueueDvo(
                                queueName = "2",
                                isSelected = false
                            ),
                            QueueDvo(
                                queueName = "3",
                                isSelected = false
                            )
                        )
                    ),
                    CityDvo(
                        id = 1,
                        oblastType = OblastType.Odesa,
                        isSelected = false,
                        queues = listOf(
                            QueueDvo(
                                queueName = "1",
                                isSelected = false
                            ),
                            QueueDvo(
                                queueName = "2",
                                isSelected = false
                            ),
                            QueueDvo(
                                queueName = "3",
                                isSelected = false
                            )
                        )
                    ),
                    CityDvo(
                        id = 1,
                        oblastType = OblastType.Odesa,
                        isSelected = false,
                        queues = listOf(
                            QueueDvo(
                                queueName = "1",
                                isSelected = false
                            ),
                            QueueDvo(
                                queueName = "2",
                                isSelected = false
                            ),
                            QueueDvo(
                                queueName = "3",
                                isSelected = false
                            )
                        )
                    ),
                    CityDvo(
                        id = 1,
                        oblastType = OblastType.Odesa,
                        isSelected = false,
                        queues = listOf(
                            QueueDvo(
                                queueName = "1",
                                isSelected = false
                            ),
                            QueueDvo(
                                queueName = "2",
                                isSelected = false
                            ),
                            QueueDvo(
                                queueName = "3",
                                isSelected = false
                            )
                        )
                    ),
                    CityDvo(
                        id = 1,
                        oblastType = OblastType.Odesa,
                        isSelected = false,
                        queues = listOf(
                            QueueDvo(
                                queueName = "1",
                                isSelected = false
                            ),
                            QueueDvo(
                                queueName = "2",
                                isSelected = false
                            ),
                            QueueDvo(
                                queueName = "3",
                                isSelected = false
                            )
                        )
                    ),
                )
            ),
            onUiEvent = {

            },
            screenState = ScreenState.None
        )
    }
}