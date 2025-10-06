package com.example.platzicalories.presentation.tracker_overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.platzicalories.R
import com.example.platzicalories.presentation.tracker_overview.components.AddButton
import com.example.platzicalories.presentation.tracker_overview.components.DaySelector
import com.example.platzicalories.presentation.tracker_overview.components.ExpandableMeal
import com.example.platzicalories.presentation.tracker_overview.components.NutrientHeader
import com.example.platzicalories.presentation.tracker_overview.components.TrackedFoodItem
import com.example.platzicalories.presentation.tracker_overview.model.defaultMeals
import com.example.platzicalories.ui.theme.LocalSpacing
import com.example.platzicalories.ui.theme.PlatziCaloriesTheme
import java.time.LocalDate

@Composable
fun TrackerOverviewScreen(
    onNavigateToSearch: (String, Int, Int, Int) -> Unit,
    trackerOverviewViewModel: TrackerOverviewViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val state = trackerOverviewViewModel.state
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(bottom = spacing.spaceMedium)

    ) {
        item {
            NutrientHeader(state = state)
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            DaySelector(
                date = state.date,
                onPreviousDayClick = {
                    trackerOverviewViewModel.onEvent(TrackerOverviewEvent.OnPreviousDayClick)
                },
                onNextDayClick = {
                    trackerOverviewViewModel.onEvent(TrackerOverviewEvent.OnNextDayClick)
                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = spacing.spaceMedium)
            )
        }
        items(state.meals){ meal ->
            ExpandableMeal(
                meal = meal,
                onToggleClick = {
                    trackerOverviewViewModel.onEvent(TrackerOverviewEvent.OnToggleMealClick(meal))
                },
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding( horizontal = spacing.spaceMedium),
                    ) {
                        val foods = state.trackedFoods.filter {
                            it.mealType == meal.mealType
                        }
                        foods.forEach { food ->
                            TrackedFoodItem(
                                trackedFood = food,
                                onDeleteClick = {
                                    trackerOverviewViewModel.onEvent(
                                        TrackerOverviewEvent.OnDeleteTrackedFoodClick(food)
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.height(spacing.spaceMedium))
                        }
                        AddButton(
                            text = stringResource(R.string.add),
                            onClick = {
                                onNavigateToSearch(
                                    meal.name.asString(context = context),
                                    state.date.dayOfMonth,
                                    state.date.monthValue,
                                    state.date.year
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
//                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun TrackerOverviewScreenTest(
) {
    val spacing = LocalSpacing.current

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(bottom = spacing.spaceMedium)

    ) {
        item {
//            NutrientHeader()
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            DaySelector(
                date = LocalDate.now(),
                onPreviousDayClick = { /*TODO*/ },
                onNextDayClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth().padding(horizontal = spacing.spaceMedium)
            )
        }
        items(defaultMeals){ meal ->
            ExpandableMeal(
                meal = meal,
                onToggleClick = { /*TODO*/ },
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding( horizontal = spacing.spaceMedium),
                    ) { }
                },
//                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrackerOverviewScreenPreview(){
    PlatziCaloriesTheme { TrackerOverviewScreenTest() }

}