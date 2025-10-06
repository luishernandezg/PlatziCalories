package com.example.platzicalories.domain.tracker.usecase


data class TrackerUseCases(
    val trackFoodUseCase: TrackFoodUseCase,
    val searchFoodUseCase: SearchFoodUseCase,
    val getFoodsForDateUseCase: GetFoodsForDateUseCase,
    val calculateMealNutrientsUseCase: CalculateMealNutrientsUseCase,
    val deleteTrackedFoodUseCase: DeleteTrackedFoodUseCase,

)