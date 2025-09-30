package com.example.platzicalories.presentation.tracker_overview.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.platzicalories.ui.theme.LocalSpacing
import com.example.platzicalories.ui.theme.PlatziCaloriesTheme


@Composable
fun UnitDisplay(
    amount: Int,
    unit: String,
    amountTextSize: TextUnit = 20.sp,
    amountColor: Color = MaterialTheme.colorScheme.onBackground,
    unitTextSize: TextUnit = 14.sp,
    unitColor: Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier,
){

    val spacing = LocalSpacing.current

    Row {
        Text(
            text = amount.toString(),
            style = MaterialTheme.typography.titleLarge,
            fontSize = amountTextSize,
            color = amountColor,
            modifier = Modifier.alignBy(LastBaseline)

        )
        Spacer(modifier.width(spacing.spaceExtraSmall))
        Text(
            text = unit,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = unitTextSize,
            color = unitColor,
            modifier = Modifier.alignBy(LastBaseline)

        )
    }


}

@Preview(showBackground = true)
@Composable
fun UnitDisplayPreview(){
    PlatziCaloriesTheme{
        UnitDisplay(
            amount = 20,
            unit = "kg",
        )
    }

}