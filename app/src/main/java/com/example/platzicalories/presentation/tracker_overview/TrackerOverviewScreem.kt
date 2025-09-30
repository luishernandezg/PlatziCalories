package com.example.platzicalories.presentation.tracker_overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.platzicalories.presentation.tracker_overview.components.NutrientHeader
import com.example.platzicalories.ui.theme.LocalSpacing
import com.example.platzicalories.ui.theme.PlatziCaloriesTheme

@Composable
fun TrackerOverviewScreen(
    trackerOverviewViewModel: TrackerOverviewViewModel = hiltViewModel()
) {
    var spacing = LocalSpacing.current
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(bottom = spacing.spaceMedium)

    ) {
        item {
            NutrientHeader()
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
        }
    }
}

@Composable
fun TrackerOverviewScreenTest(
) {
    var spacing = LocalSpacing.current
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(bottom = spacing.spaceMedium)

    ) {
        item {
            NutrientHeader()
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrackerOverviewScreenPreview(){
    PlatziCaloriesTheme { TrackerOverviewScreenTest() }

}