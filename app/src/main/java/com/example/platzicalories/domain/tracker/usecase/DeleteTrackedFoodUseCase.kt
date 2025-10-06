package com.example.platzicalories.domain.tracker.usecase

import com.example.platzicalories.domain.tracker.model.TrackedFood
import com.example.platzicalories.domain.tracker.repository.TrackerRepository

class DeleteTrackedFoodUseCase(
    private val repository: TrackerRepository
) {
    suspend operator fun invoke(trackedFood: TrackedFood) {
        repository.deleteTrackedFood(trackedFood)
    }
}