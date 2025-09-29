package com.example.platzicalories.presentation.onboarding.age_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.platzicalories.R
import com.example.platzicalories.core.domain.util.UiEvent
import com.example.platzicalories.presentation.onboarding.components.ActionButton
import com.example.platzicalories.presentation.onboarding.components.UnitTextField
import com.example.platzicalories.ui.theme.LocalSpacing

@Composable
fun AgeScreen(
    snackbarHostState: SnackbarHostState,
    onNextScreen: () -> Unit,
    ageViewModel: AgeViewModel = hiltViewModel()
){
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        ageViewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Success -> onNextScreen()
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message.asString(context = context)
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
                text = stringResource(R.string.whats_your_age),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = ageViewModel.age,
                onValueChange = ageViewModel::onAgeEnter,
                unit = stringResource(id = R.string.years)
            )
        }
        ActionButton(text = stringResource(R.string.next)
            , onClick = ageViewModel::onNextClick,
            modifier = Modifier.align(Alignment.BottomEnd),
        )

    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun AgeScreenPreview() {
//    PlatziCaloriesTheme { AgeScreen(onNextScreen = {}) }
//
//}