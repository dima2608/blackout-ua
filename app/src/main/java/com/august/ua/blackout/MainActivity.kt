package com.august.ua.blackout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.august.ua.blackout.navigation.AppNavigation
import com.august.ua.blackout.ui.components.AppSnackBar
import com.august.ua.blackout.ui.theme.BlackoutUaTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var uiState: MainActivityUiState by mutableStateOf(MainActivityUiState.Loading)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState
                    .onEach {
                        uiState = it
                        initAppUI(uiState)
                    }
                    .collect()
            }
        }

        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
            splashScreenViewProvider.view
                .animate()
//                .setDuration(500)
                .translationY(-100f)
                .alpha(0f)
                .withEndAction { splashScreenViewProvider.remove() }
            enableEdgeToEdge()
        }

        splashScreen.setKeepOnScreenCondition {
            when (uiState) {
                MainActivityUiState.Loading -> true
                is MainActivityUiState.Success -> false
            }
        }

        enableEdgeToEdge()
    }

    private fun initAppUI(uiState: MainActivityUiState) {
        if (uiState is MainActivityUiState.Success) {
            setContent {

                val navController = rememberNavController()

                val snackbarScope: CoroutineScope = rememberCoroutineScope()

                val snackbarHostState = remember {
                    SnackbarHostState()
                }

                BlackoutUaTheme(
                    darkTheme = false
                ) {
                    Box {
                        App(navController)
                        AppSnackBar(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                                .navigationBarsPadding()
                                .padding(horizontal = 16.dp)
                                .padding(bottom = 28.dp),
                            snackBarHostState = snackbarHostState
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun App(navController: NavHostController) {
        Box(
            Modifier.background(MaterialTheme.colorScheme.surface)
        ) {
            AppNavigation(navController)
        }
    }
}

