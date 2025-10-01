package com.example.platzicalories.core.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.platzicalories.presentation.onboarding.activity_level_screen.ActivityLevelScreen
import com.example.platzicalories.presentation.onboarding.age_screen.AgeScreen
import com.example.platzicalories.presentation.onboarding.gender_screen.GenderScreen
import com.example.platzicalories.presentation.onboarding.goal_screen.GoalScreen
import com.example.platzicalories.presentation.onboarding.height_screen.HeightScreen
import com.example.platzicalories.presentation.onboarding.nutrient_screen.NutrientGoalScreen
import com.example.platzicalories.presentation.onboarding.weight_screen.WeightScreen
import com.example.platzicalories.presentation.onboarding.welcome.WelcomeScreen
import com.example.platzicalories.presentation.search.SearchScreen
import com.example.platzicalories.presentation.tracker_overview.TrackerOverviewScreen

@Composable
fun NavigationRoot(
    shouldShowOnboarding: Boolean,
    snackbarHostState: SnackbarHostState,
    navHostController: NavHostController
){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        NavHost(
            navController = navHostController,
            startDestination = if(shouldShowOnboarding) WelcomeScreenRoute else TrackerOverviewScreenRoute
        ){
            composable<WelcomeScreenRoute>{
                WelcomeScreen{
                    navHostController.navigate(GenderScreenRoute)
                }
            }
            composable<GenderScreenRoute>{
                GenderScreen(
                    onNextScreen = {
                        navHostController.navigate(AgeScreenRoute)
                    },
                )
            }
            composable<AgeScreenRoute>{
                AgeScreen(
                    snackbarHostState = snackbarHostState,
                    onNextScreen = {
                        navHostController.navigate(HeightScreenRoute)
                    },
                )
            }
            composable<HeightScreenRoute>{
                HeightScreen(
                    snackbarHostState = snackbarHostState,
                    onNextScreen = {
                        navHostController.navigate(WeightScreenRoute)
                    }
                )
            }
            composable<WeightScreenRoute>{
                WeightScreen(
                    snackbarState = snackbarHostState,
                    onNextScreen = {
                        navHostController.navigate(ActivityLevelScreenRoute)
                    }
                )
            }
            composable<ActivityLevelScreenRoute>{
                ActivityLevelScreen(
                    onNextScreen = {
                        navHostController.navigate(GoalScreenRoute)
                    },
                )
            }
            composable<GoalScreenRoute>{
                GoalScreen(
                    onNextScreen = {
                        navHostController.navigate(NutrientGoalScreenRoute)
                    }
                )
            }
            composable<NutrientGoalScreenRoute>{
                NutrientGoalScreen(
                    snackbarState = snackbarHostState,
                    onNextScreen = {
                        navHostController.navigate(TrackerOverviewScreenRoute)
                    },
                )
            }
            composable<TrackerOverviewScreenRoute>{
                TrackerOverviewScreen(
                    onNavigateToSearch = {
                        navHostController.navigate(SearchScreenRoute)
                    }
                )
            }
            composable<SearchScreenRoute>{
                SearchScreen(
                    snackbarHostState = snackbarHostState,
                    mealName = "Breakfast",
                    dayOfMonth = 1,
                    month = 1,
                    year = 2022,
                    onNavigateUp = {
                        navHostController.navigateUp()
                    }
                )
            }
        }
    }
}