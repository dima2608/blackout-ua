package com.august.ua.blackout.presentation.create_update_location

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.august.ua.blackout.R
import com.august.ua.blackout.data.dto.Dummy
import com.august.ua.blackout.data.dto.OblastResponseDto
import com.august.ua.blackout.data.dto.OblastType
import com.august.ua.blackout.data.dto.OutragesResponseDto
import com.august.ua.blackout.data.dto.mapToUserLocationShiftListDbo
import com.august.ua.blackout.data.dvo.CityDvo
import com.august.ua.blackout.data.dvo.LocationColorType
import com.august.ua.blackout.data.dvo.LocationIconType
import com.august.ua.blackout.data.local.db.dbo.UserLocationDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.UserLocationOutrageDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.UserLocationShiftDbo
import com.august.ua.blackout.domain.ResultState
import com.august.ua.blackout.domain.repository.BlackoutRepository
import com.august.ua.blackout.domain.repository.UserLocationsRepository
import com.august.ua.blackout.presentation.common.NavigationEvent
import com.august.ua.blackout.presentation.common.ScreenState
import com.august.ua.blackout.presentation.create_update_location.CreateUpdateLocationPage.*
import com.august.ua.blackout.presentation.create_update_location.event.CreateUpdateLocationEvent
import com.august.ua.blackout.presentation.create_update_location.event.CreateUpdateLocationEvent.*
import com.august.ua.blackout.presentation.create_update_location.form.CreateUpdateLocationForm
import com.august.ua.blackout.presentation.create_update_location.state.CreateUpdateLocationState
import com.august.ua.blackout.presentation.create_update_location.state.CreateUpdateLocationState.*
import com.august.ua.blackout.ui.common.extensions.isNotConnected
import com.august.ua.blackout.ui.common.extensions.isNotificationPermissionGranted
import com.august.ua.blackout.ui.infrastructure.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateUpdateLocationViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val blackoutRepository: BlackoutRepository,
    private val locationsRepository: UserLocationsRepository
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
        viewModelScope.launch {
            setLocationOrdinal()
            initForm()
        }
        //initLocations()
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
            val ordinal = locationsRepository.getLocationsTableSize() + 1
            form.locationOrdinal = ordinal
            val response = blackoutRepository.getOblasts()
            when (response) {
                is ResultState.Error -> showError(response.errorDvo.toString())
                is ResultState.Success -> {
                    val cities = response as OblastResponseDto
                    //blackoutRepository.saveCities(cities)
                }
            }
            blackoutRepository.saveCities(Dummy.dummyOblastResponseDtoDto)
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
                    selectedIconType = form.selectedIconType
                )

                Push -> LocationPushState(
                    toolbar = R.string.create_location,
                    isOn = form.isPushOn
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
            is OnPushOn -> onPushOn(event.isOn)
            PreviousScreen -> onBackPressed()
            is QueueChanged -> queueChanged(event.queue)
        }
    }

    private fun resetScreenState() {
        _screenState.update { ScreenState.None }
    }

    private fun showError(error: String) {
        _screenState.update { ScreenState.ErrorState(error) }
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
        configUiState()
    }

    private fun onPushOn(on: Boolean) {
        //toDO add custom logig
        if (_screenState.replayCache.firstOrNull() == ScreenState.Loading) {
            return
        }

        if(!getApplication<Application>().isNotificationPermissionGranted()) {

        }

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
            _screenState.update { ScreenState.Loading }
            saveLocation(Dummy.dummyOutragesResponseDto)
            _screenState.update { ScreenState.None }
        }
    }

    private suspend fun saveLocation(outrages: OutragesResponseDto) {
        val location = UserLocationOutrageDbo(
            isLocationPushEnabled = form.isPushOn,
            locationIconType = form.selectedIconType,
            locationColorType = form.selectedColorType,
            locationName = form.name.toString(),
            locationOrder = form.locationOrdinal,
            selectedLocation = form.selectedCity?.oblastType ?: OblastType.Unknown,
            selectedQueue = form.selectedQueue.toString(),
            date = outrages.accessDate,
            shifts = outrages.outrages.mapToUserLocationShiftListDbo(
                oblastType = form.selectedCity?.oblastType,
                selectedQueue = form.selectedQueue
            )
        )
        locationsRepository.saveUserLocationLocal(location)
    }
}