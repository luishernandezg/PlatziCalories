package com.example.platzicalories.presentation.onboarding.gender_screen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.platzicalories.R
import com.example.platzicalories.presentation.onboarding.components.ActionButton
import com.example.platzicalories.presentation.onboarding.components.SelectableButton
import com.example.platzicalories.ui.theme.LocalSpacing
import com.example.platzicalories.ui.theme.PlatziCaloriesTheme

@Composable
fun GenderScreen(onNextScreen: () -> Unit,) {

    val spacing = LocalSpacing.current

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
            Text(text = stringResource(R.string.whats_your_gender),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row {
                SelectableButton(text = stringResource(R.string.male),
                    isSelected = true,
                    color = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = { /*TODO*/ },
                    textStyle = MaterialTheme.typography.titleSmall
                        .copy(fontWeight = FontWeight.Normal)
                )
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                SelectableButton(text = stringResource(R.string.female),
                    isSelected = false,
                    color = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = { /*TODO*/ },
                    textStyle = MaterialTheme.typography.titleSmall
                        .copy(fontWeight = FontWeight.Normal)
                )
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                SelectableButton(text = stringResource(R.string.other),
                    isSelected = false,
                    color = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = { /*TODO*/ },
                    textStyle = MaterialTheme.typography.titleSmall
                        .copy(fontWeight = FontWeight.Normal)
                )
            }

        }
        ActionButton(text = stringResource(R.string.next)
            , onClick = {onNextScreen()},
            modifier = Modifier.align(Alignment.BottomEnd),
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GenderScreenPreview() {
    PlatziCaloriesTheme {
        GenderScreen(onNextScreen = {})
    }
}