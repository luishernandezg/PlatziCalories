package com.example.platzicalories.presentation.search.model

import com.example.platzicalories.domain.tracker.model.TrackableFood

data class TrackableFoodUiState(
    val food: TrackableFood,
    val isExpanded: Boolean = false,
    val amount: String = ""
)

