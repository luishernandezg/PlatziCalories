package com.example.platzicalories.domain.tracker.usecase

import com.example.platzicalories.domain.tracker.model.TrackedFood
import com.example.platzicalories.domain.tracker.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetFoodsForDateUseCase(
    private val trackerRepository: TrackerRepository
) {
    operator fun invoke(date: LocalDate): Flow<List<TrackedFood>> {
        return trackerRepository.getFoodsForDate(date)
    }
}