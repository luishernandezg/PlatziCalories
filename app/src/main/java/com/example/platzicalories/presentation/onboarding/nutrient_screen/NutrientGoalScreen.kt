package com.example.platzicalories.presentation.onboarding.nutrient_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.platzicalories.R
import com.example.platzicalories.core.domain.util.UiEvent
import com.example.platzicalories.presentation.onboarding.components.ActionButton
import com.example.platzicalories.presentation.onboarding.components.UnitTextField
import com.example.platzicalories.ui.theme.LocalSpacing
import com.example.platzicalories.ui.theme.PlatziCaloriesTheme

@Composable
fun NutrientGoalScreen(
    snackbarState: SnackbarHostState,
    onNextScreen: () -> Unit,
    nutrientGoalViewModel: NutrientGoalViewModel
){
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        nutrientGoalViewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextScreen()
                is UiEvent.ShowSnackbar -> {
                    snackbarState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }

                else -> Unit
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(spacing.spaceMedium)
    ) {
        Column(modifier = Modifier
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                text = stringResource(R.string.what_are_your_nutrient_goals),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = nutrientGoalViewModel.state.carbsRatio ,
                onValueChange = {
                    nutrientGoalViewModel.onEvent(
                        NutrientGoalEvent.OnCarbRatioEnter(it)
                    )
                },
                unit = stringResource(id = R.string.carbs)
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = nutrientGoalViewModel.state.proteinRatio,
                onValueChange = {
                    nutrientGoalViewModel.onEvent(
                        NutrientGoalEvent.OnProteinRatioEnter(it)
                    )
                },
                unit = stringResource(id = R.string.protein)
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = nutrientGoalViewModel.state.fatRatio,
                onValueChange = {
                    nutrientGoalViewModel.onEvent(
                        NutrientGoalEvent.OnFatRatioEnter(it)
                    )
                },
                unit = stringResource(id = R.string.fat)
            )
        }
        ActionButton(text = stringResource(R.string.next)
            , onClick = {
                nutrientGoalViewModel.onEvent(NutrientGoalEvent.OnNextClick)
            },
            modifier = Modifier.align(Alignment.BottomEnd),
        )

    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//private fun NutrientGoalScreenPreview() {
//    PlatziCaloriesTheme { NutrientGoalScreen(onNextScreen = {}) }
//
//}