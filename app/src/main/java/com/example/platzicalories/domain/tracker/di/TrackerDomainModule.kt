package com.example.platzicalories.domain.tracker.di

import com.example.platzicalories.core.domain.preferences.Preferences
import com.example.platzicalories.domain.tracker.repository.TrackerRepository
import com.example.platzicalories.domain.tracker.usecase.CalculateMealNutrientsUseCase
import com.example.platzicalories.domain.tracker.usecase.DeleteTrackedFoodUseCase
import com.example.platzicalories.domain.tracker.usecase.GetFoodsForDateUseCase
import com.example.platzicalories.domain.tracker.usecase.SearchFoodUseCase
import com.example.platzicalories.domain.tracker.usecase.TrackFoodUseCase
import com.example.platzicalories.domain.tracker.usecase.TrackerUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCase(
        trackerRepository: TrackerRepository,
        preferences: Preferences
    ): TrackerUseCases {
        return TrackerUseCases(
            trackFoodUseCase = TrackFoodUseCase(trackerRepository),
            searchFoodUseCase = SearchFoodUseCase(trackerRepository),
            getFoodsForDateUseCase = GetFoodsForDateUseCase(trackerRepository),
            calculateMealNutrientsUseCase = CalculateMealNutrientsUseCase(preferences),
            deleteTrackedFoodUseCase = DeleteTrackedFoodUseCase(trackerRepository)
        )
    }
}