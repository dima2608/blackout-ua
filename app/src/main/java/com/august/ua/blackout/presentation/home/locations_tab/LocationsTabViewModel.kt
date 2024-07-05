package com.august.ua.blackout.presentation.home.locations_tab

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LocationsTabViewModel @Inject constructor(
    @ApplicationContext context: Context,

): AndroidViewModel(context as Application) {

}