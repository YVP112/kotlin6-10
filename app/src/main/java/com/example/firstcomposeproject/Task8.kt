package com.example.firstcomposeproject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val primaryColor = Color(0xFF3F5F94)
private val secondaryColor = Color(0xFF555E70)
private val buttonTextColor = Color.White

// Теги нужны для UI-тестов (задание 9)
object WaterTags {
    const val COUNT = "water_count"
    const val ADD = "add_button"
    const val FINISH = "finish_day_button"
    const val STREAK = "streak_text"
}

@Composable
fun WaterTracker() {
    var waterCount by rememberSaveable { mutableIntStateOf(0) }   // выпито за день, мл
    var streakDays by rememberSaveable { mutableIntStateOf(0) }   // дней подряд >= 1500 мл

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Трекер воды",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = primaryColor
        )
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = "$waterCount мл",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = secondaryColor,
            modifier = Modifier.testTag(WaterTags.COUNT)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            onClick = { waterCount += 250 },
            modifier = Modifier.testTag(WaterTags.ADD)
        ) {
            Text(
                text = "+250 мл",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = buttonTextColor
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            onClick = {
                streakDays = if (waterCount >= 1500) streakDays + 1 else 0
                waterCount = 0
            },
            modifier = Modifier.testTag(WaterTags.FINISH)
        ) {
            Text(
                text = "Завершить день",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = buttonTextColor
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Дней подряд: $streakDays",
            fontSize = 18.sp,
            color = secondaryColor,
            modifier = Modifier.testTag(WaterTags.STREAK)
        )
        Text(
            text = "(1500 мл и больше)",
            fontSize = 12.sp,
            color = secondaryColor
        )
    }
}

@Preview(name = "Задание 8", showBackground = true, showSystemUi = true)
@Composable
fun WaterTrackerPreview() {
    WaterTracker()
}
