package com.example.platzicalories.presentation.tracker_overview

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.platzicalories.core.domain.preferences.Preferences
import com.example.platzicalories.core.domain.util.UiEvent
import com.example.platzicalories.domain.tracker.usecase.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.io.Console
import javax.inject.Inject

@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
    private val preferences: Preferences,
    private val trackerUseCases: TrackerUseCases
): ViewModel() {

    var state by mutableStateOf(TrackerOverviewState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var getFoodsForDateJob: Job? = null

    init {
        refreshFoods()
        preferences.saveShouldShowOnboarding(false)
    }

    // TODO -> Only for test, remove later
    private fun executeSearch(){
        viewModelScope.launch {
            trackerUseCases.searchFoodUseCase("egg")
        }
    }

    fun onEvent(event: TrackerOverviewEvent){
        when(event){
            is TrackerOverviewEvent.OnToggleMealClick -> {
                state = state.copy(
                    meals = state.meals.map {
                        if(it.name == event.meal.name){
                            it.copy(isExpanded = !it.isExpanded)
                        } else it
                    }
                )
            }
//            is TrackerOverviewEvent.OnNextDayClick -> {
//                state = state.copy(
//                    date = state.date.plusDays(1)
//                )
//                executeSearch()
//            }
//            is TrackerOverviewEvent.OnPreviousDayClick -> {
//                state = state.copy(
//                    date = state.date.minusDays(1)
//                )
//                executeSearch()
//            }
//
//            is TrackerOverviewEvent.OnDeleteTrackedFoodClick -> {
//                viewModelScope.launch {
//                    trackerUseCases.deleteTrackedFoodUseCase(event.trackedFood)
//                    refreshFoods()
//                }
//            }
            else -> {}
        }
    }

    private fun refreshFoods() {
        getFoodsForDateJob?.cancel()
        getFoodsForDateJob = trackerUseCases
            .getFoodsForDateUseCase(state.date)
            .onEach { foods ->
                val nutrientsResult = trackerUseCases.calculateMealNutrientsUseCase(foods)
                state = state.copy(
                    totalCarbs = nutrientsResult.totalCarbs,
                    totalProtein = nutrientsResult.totalProtein,
                    totalFat = nutrientsResult.totalFat,
                    totalCalories = nutrientsResult.totalCalories,
                    carbsGoal = nutrientsResult.carbsGoal,
                    proteinGoal = nutrientsResult.proteinGoal,
                    fatGoal = nutrientsResult.fatGoal,
                    caloriesGoal = nutrientsResult.caloriesGoal,
                    trackedFoods = foods,
                    meals = state.meals.map {
                        val nutrientsForMeal =
                            nutrientsResult.mealNutrients[it.mealType]
                                ?: return@map it.copy(
                                    carbs = 0,
                                    protein = 0,
                                    fat = 0,
                                    calories = 0
                                )
                        it.copy(
                            carbs = nutrientsForMeal.carbs,
                            protein = nutrientsForMeal.protein,
                            fat = nutrientsForMeal.fat,
                            calories = nutrientsForMeal.calories
                        )
                    }
                )
            }
            .launchIn(viewModelScope)
    }

}