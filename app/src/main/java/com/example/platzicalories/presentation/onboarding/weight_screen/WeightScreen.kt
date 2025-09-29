package com.example.platzicalories.presentation.onboarding.weight_screen

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
fun WeightScreen(
    snackbarState: SnackbarHostState,
    weightViewModel: WeightViewModel,
    onNextScreen: () -> Unit){

    val spacing = LocalSpacing.current
    var weight by remember { mutableStateOf("180") }
    var context = LocalContext.current

    LaunchedEffect(key1 = true) {
        weightViewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Success -> onNextScreen()
                is UiEvent.ShowSnackbar -> {
                    snackbarState.showSnackbar(
                        message = event.message.asString(context))
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
                text = stringResource(R.string.whats_your_weight),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = weightViewModel.weight,
                onValueChange = weightViewModel::onWeightEnter,
                unit = stringResource(id = R.string.kg)
            )
        }
        ActionButton(text = stringResource(R.string.next)
            , onClick = weightViewModel::onNextClick,
            modifier = Modifier.align(Alignment.BottomEnd),
        )

    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun WeightScreenPreview() {
//    PlatziCaloriesTheme { WeightScreen(onNextScreen = {}) }
//}