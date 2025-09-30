package com.example.platzicalories.presentation.tracker_overview.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.platzicalories.ui.theme.FatColor
import com.example.platzicalories.ui.theme.PlatziCaloriesTheme
import com.example.platzicalories.ui.theme.ProteinColor
import com.example.platzicalories.ui.theme.tertiaryDark

@Composable
fun NutrientsBar(
    carbs: Int,
    protein: Int,
    fat: Int,
    calories: Int,
    calorieGoal: Int,
    modifier: Modifier = Modifier
){
    val background = MaterialTheme.colorScheme.background
    val caloriesExceedColor = MaterialTheme.colorScheme.error
    val carbWidthRatio = remember {
        Animatable(0f)
    }
    val proteinWidthRatio = remember {
        Animatable(0f)
    }
    val fatWidthRatio = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = carbs) {
        carbWidthRatio.animateTo(
            targetValue = ((carbs * 4f) / calorieGoal)
        )
    }
    LaunchedEffect(key1 = protein) {
        proteinWidthRatio.animateTo(
            targetValue = ((protein * 4f) / calorieGoal)
        )
    }
    LaunchedEffect(key1 = fat) {
        fatWidthRatio.animateTo(
            targetValue = ((fat * 9f) / calorieGoal)
        )
    }

    Canvas(modifier = modifier) {
        if(calories <= calorieGoal) {
            val carbsWidth = carbWidthRatio.value * size.width
            val proteinWidth = proteinWidthRatio.value * size.width
            val fatWidth = fatWidthRatio.value * size.width
            drawRoundRect(
                color = background,
                size = size,
                cornerRadius = CornerRadius(20f)
            )
            drawRoundRect(
                color = FatColor,
                size = Size(
                    width = carbsWidth + proteinWidth + fatWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(20f)
            )
            drawRoundRect(
                color = ProteinColor,
                size = Size(
                    width = carbsWidth + proteinWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(20f)
            )
            drawRoundRect(
                color = tertiaryDark,
                size = Size(
                    width = carbsWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(20f)
            )
        } else {
            drawRoundRect(
                color = caloriesExceedColor,
                size = size,
                cornerRadius = CornerRadius(20f)
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun NutrientsBarPreview(){
    PlatziCaloriesTheme{
        NutrientsBar(
            carbs = 100,
            protein = 200,
            fat = 100,
            calories = 2000,
            calorieGoal = 3500,
            modifier = Modifier.fillMaxWidth().height(30.dp)
        )
    }
}