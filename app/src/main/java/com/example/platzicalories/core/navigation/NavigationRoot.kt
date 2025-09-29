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
import com.example.platzicalories.presentation.onboarding.activity_level_screen.ActivityLevelViewModel
import com.example.platzicalories.presentation.onboarding.age_screen.AgeScreen
import com.example.platzicalories.presentation.onboarding.age_screen.AgeViewModel
import com.example.platzicalories.presentation.onboarding.gender_screen.GenderScreen
import com.example.platzicalories.presentation.onboarding.gender_screen.GenderViewModel
import com.example.platzicalories.presentation.onboarding.goal_screen.GoalScreen
import com.example.platzicalories.presentation.onboarding.goal_screen.GoalViewModel
import com.example.platzicalories.presentation.onboarding.height_screen.HeightScreen
import com.example.platzicalories.presentation.onboarding.height_screen.HeightViewModel
import com.example.platzicalories.presentation.onboarding.nutrient_screen.NutrientGoalScreen
import com.example.platzicalories.presentation.onboarding.nutrient_screen.NutrientGoalViewModel
import com.example.platzicalories.presentation.onboarding.weight_screen.WeightScreen
import com.example.platzicalories.presentation.onboarding.weight_screen.WeightViewModel
import com.example.platzicalories.presentation.onboarding.welcome.WelcomeScreen
import com.example.platzicalories.presentation.tracker_overview.TrackerOverviewScreen

@Composable
fun NavigationRoot(
    snackbarHostState: SnackbarHostState,
    navHostController: NavHostController
){
    val genderViewModel = GenderViewModel()
    val ageViewModel = AgeViewModel()
    val heightViewModel = HeightViewModel()
    val weightViewModel = WeightViewModel()
    val activityLevelViewModel = ActivityLevelViewModel()
    val goalViewModel = GoalViewModel()
    val nutrientGoalViewModel = NutrientGoalViewModel()


    Box(
        modifier = Modifier.fillMaxSize()
    ){
        NavHost(
            navController = navHostController,
            startDestination = WelcomeScreenRoute
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
                    genderViewModel = genderViewModel
                )
            }
            composable<AgeScreenRoute>{
                AgeScreen(
                    snackbarHostState = snackbarHostState,
                    onNextScreen = {
                        navHostController.navigate(HeightScreenRoute)
                    },
                    ageViewModel = ageViewModel
                )
            }
            composable<HeightScreenRoute>{
                HeightScreen(
                    snackbarHostState = snackbarHostState,
                    heightViewModel = heightViewModel,
                    onNextScreen = {
                        navHostController.navigate(WeightScreenRoute)
                    }
                )
            }
            composable<WeightScreenRoute>{
                WeightScreen(
                    snackbarState = snackbarHostState,
                    weightViewModel = weightViewModel,
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
                    activityLevelViewModel = activityLevelViewModel
                )
            }
            composable<GoalScreenRoute>{
                GoalScreen(
                    goalViewModel = goalViewModel,
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
                    nutrientGoalViewModel = nutrientGoalViewModel
                )
            }
            composable<TrackerOverviewScreenRoute>{
                TrackerOverviewScreen()
            }
        }
    }
}