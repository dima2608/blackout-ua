package com.august.ua.blackout.presentation.select_oblast

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.august.ua.blackout.data.dto.OblastType
import com.august.ua.blackout.data.dvo.OblastsDvo
import com.august.ua.blackout.presentation.common.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SelectOblastViewModel @Inject constructor(
    @ApplicationContext context: Context,
    //val getOblastsUseCase: GetOblastsUseCase
): AndroidViewModel(context as Application) {

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.None)
    val screenState = _screenState.asStateFlow()

    private val _dataState = MutableStateFlow<OblastsDvo?>(null)
    val dataState = _dataState.asStateFlow()

    init {
        getOblasts()
    }

    private fun getOblasts() {
//        if (getApplication<Application>().isC) {
//            return
//        } toDo add connectivity check

        //_dataState.tryEmit(OblastsDvo(oblasts = OblastType.entries))

    }

    private fun showError(error: String) {
        _screenState.update { ScreenState.ErrorState(error) }
    }
}