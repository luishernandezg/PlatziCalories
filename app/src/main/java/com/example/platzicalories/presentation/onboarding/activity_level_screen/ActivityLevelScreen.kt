package com.example.platzicalories.presentation.onboarding.activity_level_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.platzicalories.R
import com.example.platzicalories.core.domain.model.ActivityLevel
import com.example.platzicalories.core.domain.util.UiEvent
import com.example.platzicalories.presentation.onboarding.components.ActionButton
import com.example.platzicalories.presentation.onboarding.components.SelectableButton
import com.example.platzicalories.ui.theme.LocalSpacing

@Composable
fun ActivityLevelScreen(
    activityLevelViewModel: ActivityLevelViewModel = hiltViewModel(),
    onNextScreen: () -> Unit,
    ) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        activityLevelViewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Success -> onNextScreen()
                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(spacing.spaceMedium)
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(R.string.whats_your_activity_level),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row {
                SelectableButton(text = stringResource(R.string.low),
                    isSelected = activityLevelViewModel.selectedActivityLevel is ActivityLevel.Low,
                    color = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = {
                        activityLevelViewModel.onActivityLevelClick(ActivityLevel.Low)
                    },
                    textStyle = MaterialTheme.typography.titleSmall
                        .copy(fontWeight = FontWeight.Normal)
                )
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                SelectableButton(text = stringResource(R.string.medium),
                    isSelected = activityLevelViewModel.selectedActivityLevel is ActivityLevel.Medium,
                    color = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = {
                        activityLevelViewModel.onActivityLevelClick(ActivityLevel.Medium)
                    },
                    textStyle = MaterialTheme.typography.titleSmall
                        .copy(fontWeight = FontWeight.Normal)
                )
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                SelectableButton(text = stringResource(R.string.lose),
                    isSelected = activityLevelViewModel.selectedActivityLevel is ActivityLevel.High,
                    color = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = {
                        activityLevelViewModel.onActivityLevelClick(ActivityLevel.High)
                    },
                    textStyle = MaterialTheme.typography.titleSmall
                        .copy(fontWeight = FontWeight.Normal)
                )

            }
        }
        ActionButton(text = stringResource(R.string.next)
            , onClick = activityLevelViewModel::onNextClick,
            modifier = Modifier.align(Alignment.BottomEnd),
        )
    }
}

//@Preview(
//    showBackground = true,
//    showSystemUi = true
//)
//@Composable
//private fun ActivityLevelScreenPreview() {
//    PlatziCaloriesTheme {
//        ActivityLevelScreen(onNextScreen = {})
//    }
//}