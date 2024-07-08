package com.august.ua.blackout.presentation.home

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.august.ua.blackout.MainActivity
import com.august.ua.blackout.data.dto.Dummy
import com.august.ua.blackout.data.dto.OblastResponseDto
import com.august.ua.blackout.domain.ResultState
import com.august.ua.blackout.domain.repository.BlackoutRepository
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val blackoutRepository: BlackoutRepository
): AndroidViewModel(context as Application) {

    init {
        getAvailableCities()
        initFcm()
    }
    private fun getAvailableCities() {
        viewModelScope.launch {
//            val response = blackoutRepository.getOblast()
//
//            when(response) {
//                is ResultState.Error -> TODO()
//                is ResultState.Success -> saveAvailableCities(response as OblastResponseDto?)
//            }
            //saveAvailableCities(Dummy.dummyOblastResponseDtoDto)
        }

    }

    private fun getSavedLocationQueues() {

    }

    private suspend fun saveAvailableCities(cities: OblastResponseDto?) {
        if (cities == null || cities.oblasts.isNullOrEmpty()) return

        blackoutRepository.saveCities(cities)

    }

    private fun initFcm() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            val token = task.result
            Log.d(MainActivity::class.java.simpleName, "token =========>>>>>> $token")
            //sendFcmToken(token = token)
        })
    }
}