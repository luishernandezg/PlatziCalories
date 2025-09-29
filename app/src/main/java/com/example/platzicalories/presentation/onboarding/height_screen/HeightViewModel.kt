package com.example.platzicalories.presentation.onboarding.height_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.platzicalories.core.domain.use_case.FilterOutDigits
import com.example.platzicalories.core.domain.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import androidx.lifecycle.viewModelScope
import com.example.platzicalories.R
import com.example.platzicalories.core.domain.preferences.Preferences
import com.example.platzicalories.core.domain.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits
): ViewModel() {
    var height by mutableStateOf("20")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onHeightEnter(height: String) {
        if(height.length <= 3) {
            this.height = filterOutDigits(height)
        }
    }

    fun onNextClick(){
        viewModelScope.launch {
            val heightNumber = height.toIntOrNull() ?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackbar(
                        UiText.StringResource(R.string.error_height_cant_be_empty)
                    )
                )
                return@launch
            }
            preferences.saveHeight(heightNumber)
            _uiEvent.send(UiEvent.Success)
        }
    }
}