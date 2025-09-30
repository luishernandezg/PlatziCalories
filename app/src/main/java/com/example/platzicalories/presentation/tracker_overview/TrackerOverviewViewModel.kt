package com.example.platzicalories.presentation.tracker_overview

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.platzicalories.core.domain.preferences.Preferences
import com.example.platzicalories.domain.tracker.usecase.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.Console
import javax.inject.Inject

@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
    private val preferences: Preferences,
    private val trackerUseCases: TrackerUseCases
): ViewModel() {
    init {
        preferences.saveShouldShowOnboarding(false)

        executeSearch()

    }

    // TODO -> Only for test, remove later
    private fun executeSearch(){
        viewModelScope.launch {
            trackerUseCases.searchFoodUseCase("egg")
        }

    }

}