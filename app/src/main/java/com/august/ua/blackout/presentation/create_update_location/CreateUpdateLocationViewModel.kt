package com.august.ua.blackout.presentation.create_update_location

import android.app.Application
import android.content.Context
import android.provider.Settings
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.august.ua.blackout.MainActivity
import com.august.ua.blackout.R
import com.august.ua.blackout.data.dto.OblastResponseDto
import com.august.ua.blackout.data.dto.OblastType
import com.august.ua.blackout.data.dto.OutragePushTime
import com.august.ua.blackout.data.dto.OutragesResponseDto
import com.august.ua.blackout.data.dto.UserDto
import com.august.ua.blackout.data.dto.mapToUserLocationShiftListDbo
import com.august.ua.blackout.data.dvo.CityDvo
import com.august.ua.blackout.data.dvo.LocationColorType
import com.august.ua.blackout.data.dvo.LocationIconType
import com.august.ua.blackout.data.dvo.PushTimeDvo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.UserLocationOutrageDbo
import com.august.ua.blackout.domain.ResultState
import com.august.ua.blackout.domain.common.EMPTY_STRING
import com.august.ua.blackout.domain.common.parseCalendarEventTime
import com.august.ua.blackout.domain.common.parseDayTime
import com.august.ua.blackout.domain.repository.BlackoutRepository
import com.august.ua.blackout.domain.repository.UserLocationsRepository
import com.august.ua.blackout.domain.repository.user.UserRepository
import com.august.ua.blackout.navigation.Screen
import com.august.ua.blackout.presentation.common.NavigationEvent
import com.august.ua.blackout.presentation.common.ScreenState
import com.august.ua.blackout.presentation.create_update_location.CreateUpdateLocationPage.*
import com.august.ua.blackout.presentation.create_update_location.event.CreateUpdateLocationEvent
import com.august.ua.blackout.presentation.create_update_location.event.CreateUpdateLocationEvent.*
import com.august.ua.blackout.presentation.create_update_location.form.CreateUpdateLocationForm
import com.august.ua.blackout.presentation.create_update_location.state.CreateUpdateLocationState
import com.august.ua.blackout.presentation.create_update_location.state.CreateUpdateLocationState.*
import com.august.ua.blackout.ui.common.extensions.getDeviceHardwareId
import com.august.ua.blackout.ui.common.extensions.isNotConnected
import com.august.ua.blackout.ui.common.extensions.isNotificationPermissionGranted
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.security.AccessController.getContext
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class CreateUpdateLocationViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val blackoutRepository: BlackoutRepository,
    private val locationsRepository: UserLocationsRepository,
    private val userRepository: UserRepository<Flow<UserDto>, UserDto>
) : AndroidViewModel(context as Application) {

    private var currentPage = Undefined
    private val form = CreateUpdateLocationForm()

    private val _uiState = MutableStateFlow<CreateUpdateLocationState>(Initial)
    val uiState: StateFlow<CreateUpdateLocationState> = _uiState.asStateFlow()

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.None)
    val screenState = _screenState.asStateFlow()

    private val _navEvent = MutableStateFlow<NavigationEvent>(NavigationEvent.None)
    val navEvent = _navEvent.asStateFlow()

    init {
        initLocations()
    }

    private fun initLocations() {
        if (getApplication<Application>().isNotConnected) {
            showError(getApplication<Application>().getString(R.string.no_internet_connection))
            return
        }

        getLocations()
    }

    private fun getLocations() {

        viewModelScope.launch {
            _screenState.update { ScreenState.Loading }
            when (val response = blackoutRepository.getOblasts()) {
                is ResultState.Error -> showError(response.errorDvo.toString())
                is ResultState.Success -> {
                    val cities = response.data as OblastResponseDto
                    blackoutRepository.saveCities(cities)
                }
            }

            setLocationOrdinal()
            resetScreenState()
            initForm()
        }

    }

    private suspend fun setLocationOrdinal() {
        val ordinal = locationsRepository.getLocationsTableSize() + 1
        form.locationOrdinal = ordinal
    }

    private suspend fun initForm() {
        form.cities = blackoutRepository.getCitiesLocal()
//            userRepository.userData.first()?.let {
//                userForm.oblastType = it.oblastType
//                userForm.queue = it.queue
//            }
        initDefaultUiState()
    }

    private fun initDefaultUiState() {
        currentPage = Location
        configUiState()
    }

    private fun configUiState() {
        _uiState.update {
            when (currentPage) {
                Undefined -> Initial
                Location -> LocationState(
                    listCities = form.cities,
                    toolbar = R.string.create_location
                )

                Queue -> LocationQueueState(
                    link = "",
                    toolbar = R.string.create_location,
                    selectedCity = form.selectedCity ?: return

                )

                LocationSettings -> LocationSettingsState(
                    toolbar = R.string.create_location,
                    locationName = form.name ?: "",
                    selectedColorType = form.selectedColorType,
                    selectedIconType = form.selectedIconType,
                    locationNameError = form.locationNameError
                )

                Push -> LocationPushState(
                    toolbar = R.string.create_location,
                    pushTimes = getPushTimes(),
                    isOutragePushOn = form.isOutragePushOn
                )
            }

        }
    }

    fun onUiEvent(event: CreateUpdateLocationEvent) {
        when (event) {
            is CityChanged -> cityChanged(event.city)
            is ColorChanged -> colorChanged(event.colorType)
            is IconChanged -> iconChanged(event.iconType)
            is NameChanged -> nameChanged(event.name)
            NextScreen -> processOnNextEvent()
            is OnPushOn -> onPushOn(event.type, event.isOn)
            PreviousScreen -> onBackPressed()
            is QueueChanged -> queueChanged(event.queue)
            OnSnackbarDismissed -> resetScreenState()
            OnSnackbarRetry -> Unit
            ResetScreenState -> resetScreenState()
            is OnOutrageUpdatePushOn -> onOutrageUpdatePushOn(event.isOn)
        }
    }

    private fun resetScreenState() {
        _screenState.update { ScreenState.None }
    }

    private fun showError(error: String, customRetryTitle: String? = null) {
        _screenState.update { ScreenState.ErrorState(error, customRetryTitle) }
    }

    private fun processOnNextEvent() {
        resetScreenState()
        viewModelScope.launch {
            when (currentPage) {
                Undefined -> Unit
                Location -> {
                    if (form.isOblastSelected().not()) {
                        showError(getApplication<Application>().getString(R.string.select_location_error))
                        return@launch
                    }
                    currentPage = Queue
                    configUiState()
                }

                Queue -> {
                    if (form.isQueueSelected().not()) {
                        showError(getApplication<Application>().getString(R.string.select_queue_error))
                        return@launch
                    }
                    currentPage = LocationSettings
                    configUiState()
                }

                LocationSettings -> {
                    if (form.isNameValid().not()) {
                        form.locationNameError = R.string.please_enter_location_name
                        configUiState()
                        showError(getApplication<Application>().getString(R.string.please_enter_location_name))
                        return@launch
                    }
                    currentPage = Push
                    configUiState()
                }

                Push -> {
                    processCreatingLocation()
                }
            }
        }
    }

    private fun onBackPressed() {
        if (_screenState.replayCache.firstOrNull() == ScreenState.Loading) {
            return
        }
        resetScreenState()
        when (currentPage) {
            Undefined -> Unit
            Location -> _navEvent.update { NavigationEvent.CloseScreen }
            Queue -> {
                currentPage = Location
                configUiState()
            }

            LocationSettings -> {
                currentPage = Queue
                configUiState()
            }

            Push -> {
                currentPage = LocationSettings
                configUiState()
            }
        }
    }

    private fun cityChanged(city: CityDvo) {
        form.cities.onEach {
            if (it.oblastType == city.oblastType) {
                it.isSelected = !it.isSelected

                if (it.isSelected) form.selectedCity = city
                else form.selectedCity = null
            } else it.isSelected = false

        }
        configUiState()
    }

    private fun colorChanged(colorType: LocationColorType) {
        form.selectedColorType = colorType
        configUiState()
    }

    private fun iconChanged(iconType: LocationIconType) {
        form.selectedIconType = iconType
        configUiState()
    }

    private fun nameChanged(name: String) {
        form.name = name
        form.locationNameError =
            if (name.isNullOrBlank().not()) null else R.string.please_enter_location_name
        configUiState()
    }

    private fun onPushOn(type: OutragePushTime, isOn: Boolean) {
        if (_screenState.replayCache.firstOrNull() == ScreenState.Loading) {
            return
        }

        if (!getApplication<Application>().isNotificationPermissionGranted()) {
            showError(
                getApplication<Application>().getString(R.string.please_give_notification_permission),
                getApplication<Application>().getString(R.string.give)
            )
            return
        }

        if (isOn) {
            form.selectedPushTime = type
        } else {
            form.selectedPushTime = null
        }

        configUiState()
    }

    private fun queueChanged(queue: String) {
        form.selectedCity?.queues?.onEach {
            if (it.queueName == queue) {
                it.isSelected = !it.isSelected
                if (it.isSelected) form.selectedQueue = queue
                else form.selectedQueue = null
            } else it.isSelected = false
        }
        configUiState()
    }

    private fun processCreatingLocation() {
        viewModelScope.launch {

            if (getApplication<Application>().isNotConnected) {
                _screenState.update { ScreenState.NoInternetConnection }
                return@launch
            }

            _screenState.update { ScreenState.Loading }

            if (form.selectedCity?.oblastType == null || form.selectedQueue == null) return@launch

            confirmIsUserExist()

            _screenState.update { ScreenState.Loading }

            val current = ZonedDateTime.now(ZoneOffset.UTC)

            val response = blackoutRepository.getOutrage(
                date = current.parseDayTime(),
                listOf(form.selectedCity?.oblastType!!),
                listOf(form.selectedQueue!!)
            )

            resetScreenState()
            when (response) {
                is ResultState.Error -> showError(response.errorDvo.toString())
                is ResultState.Success -> {
                    val outrage = response.data as OutragesResponseDto
                    saveLocation(outrage) { isSuccess ->
                        if (!isSuccess) {
                            showError(getApplication<Application>().getString(R.string.user_creation_error))
                            return@saveLocation
                        }
                    }
                    updateUser()
                }
            }
        }
    }

    private suspend fun saveLocation(
        outrages: OutragesResponseDto,
        callBack: (isSuccess: Boolean) -> Unit
    ) {
        if (getUserId().isNullOrBlank()) {
            callBack(false)
            return
        } else {
            val location = UserLocationOutrageDbo(
                isLocationPushEnabled = form.isPushOn,
                locationIconType = form.selectedIconType,
                locationColorType = form.selectedColorType,
                locationName = form.name.toString(),
                locationOrder = form.locationOrdinal,
                selectedLocation = form.selectedCity?.oblastType ?: OblastType.Unknown,
                selectedQueue = form.selectedQueue.toString(),
                date = outrages.accessDate,
                selectedPushTime = form.selectedPushTime,
                isOutragePushEnabled = form.isOutragePushOn,
                userId = getUserId()!!,
            )
            val locationId = locationsRepository.saveUserLocationLocal(location)

            val shifts = outrages.outrages.mapToUserLocationShiftListDbo(
                oblastType = form.selectedCity?.oblastType,
                selectedQueue = form.selectedQueue,
                locationId = locationId
            )

            val date = outrages.outrages?.firstOrNull()?.date ?: EMPTY_STRING
            locationsRepository.saveLocationShifts(shifts, date)

            callBack(true)
        }
    }

    private fun getPushTimes(): List<PushTimeDvo> {
        val pushTimes = OutragePushTime.entries.map {
            PushTimeDvo(
                isSelected = form.selectedPushTime == it,
                type = it
            )
        }
        return pushTimes
    }

    private suspend fun getUserId() = userRepository.getUser()?.id

    private fun onOutrageUpdatePushOn(isOn: Boolean) {
        form.isOutragePushOn = isOn
        configUiState()
    }

    private suspend fun isUserExist() = getUserId() != null

    private suspend fun confirmIsUserExist() {
        if (!isUserExist()) createUser()
    }

    private suspend fun createUser() {
        _screenState.update { ScreenState.Loading }
        if (getApplication<Application>().isNotConnected) {
            _screenState.update { ScreenState.NoInternetConnection }
            return
        }

        val userDto = UserDto(
            deviceId = getApplication<Application>().getDeviceHardwareId()
        )

        val response = userRepository.createUser(userDto)
        resetScreenState()
        when (response) {
            is ResultState.Error -> showError(response.errorDvo.toString())
            is ResultState.Success -> {
                val data = response.data as UserDto

                userRepository.saveNewUserData(data)
            }
        }
    }

    private suspend fun updateUser() {
        if (getApplication<Application>().isNotConnected) {
            _screenState.update { ScreenState.NoInternetConnection }
            return
        }

        val response =
            userRepository.updateUser(getApplication<Application>().getDeviceHardwareId())

        when (response) {
            is ResultState.Error -> showError(response.errorDvo.toString())
            is ResultState.Success -> {
                val data = response.data as UserDto

                userRepository.saveNewUserData(data)
                navigateToHome()
            }
        }
    }

    private fun navigateToHome() {
        _navEvent.update { NavigationEvent.NavigateTo(Screen.HomeScreen.route) }
    }

    private fun initFcm() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            val token = task.result
            Log.d(MainActivity::class.java.simpleName, "token =========>>>>>> $token")
            //send token to serv
        })
    }

}