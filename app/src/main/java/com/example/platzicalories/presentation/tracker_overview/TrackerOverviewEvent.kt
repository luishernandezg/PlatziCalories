package com.example.platzicalories.presentation.tracker_overview

import com.example.platzicalories.domain.tracker.model.TrackedFood
import com.example.platzicalories.presentation.tracker_overview.model.Meal

sealed class TrackerOverviewEvent {
    data object OnNextDayClick: TrackerOverviewEvent()
    data object OnPreviousDayClick: TrackerOverviewEvent()
    data class OnToggleMealClick(val meal: Meal) : TrackerOverviewEvent()
    data class OnDeleteTrackedFoodClick(val trackedFood: TrackedFood): TrackerOverviewEvent()
}