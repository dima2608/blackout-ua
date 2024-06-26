package com.august.ua.blackout.presentation.splash

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    @ApplicationContext context: Context,
): AndroidViewModel(context as Application) {

}