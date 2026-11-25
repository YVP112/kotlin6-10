package com.example.firstcomposeproject

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

// Задание 9: UI-тесты для WaterTracker
class WaterTrackerTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun addWater(times: Int) {
        repeat(times) {
            composeTestRule.onNodeWithTag(WaterTags.ADD).performClick()
        }
    }

    // 1. Кнопка добавления жидкости: есть и работает
    @Test
    fun addButton_isDisplayed_andIncreasesWaterBy250() {
        composeTestRule.setContent { WaterTracker() }

        composeTestRule.onNodeWithText("+250 мл").assertIsDisplayed()
        composeTestRule.onNodeWithTag(WaterTags.COUNT).assertTextEquals("0 мл")

        composeTestRule.onNodeWithTag(WaterTags.ADD).performClick()
        composeTestRule.onNodeWithTag(WaterTags.COUNT).assertTextEquals("250 мл")

        composeTestRule.onNodeWithTag(WaterTags.ADD).performClick()
        composeTestRule.onNodeWithTag(WaterTags.COUNT).assertTextEquals("500 мл")
    }

    // 2. Кнопка «Завершить день»: есть, сбрасывает счётчик воды
    @Test
    fun finishDayButton_isDisplayed_andResetsWaterCount() {
        composeTestRule.setContent { WaterTracker() }

        composeTestRule.onNodeWithText("Завершить день").assertIsDisplayed()

        addWater(3)
        composeTestRule.onNodeWithTag(WaterTags.COUNT).assertTextEquals("750 мл")

        composeTestRule.onNodeWithTag(WaterTags.FINISH).performClick()
        composeTestRule.onNodeWithTag(WaterTags.COUNT).assertTextEquals("0 мл")
    }

    // 3. Текст со счётчиком дней подряд (1500 мл и больше)
    @Test
    fun streakText_isDisplayed_andUpdatesCorrectly() {
        composeTestRule.setContent { WaterTracker() }

        // изначально 0
        composeTestRule.onNodeWithTag(WaterTags.STREAK).assertIsDisplayed()
        composeTestRule.onNodeWithTag(WaterTags.STREAK).assertTextEquals("Дней подряд: 0")

        // день 1: 1500 мл -> 1
        addWater(6)
        composeTestRule.onNodeWithTag(WaterTags.FINISH).performClick()
        composeTestRule.onNodeWithTag(WaterTags.STREAK).assertTextEquals("Дней подряд: 1")

        // день 2: 2000 мл -> 2
        addWater(8)
        composeTestRule.onNodeWithTag(WaterTags.FINISH).performClick()
        composeTestRule.onNodeWithTag(WaterTags.STREAK).assertTextEquals("Дней подряд: 2")

        // день 3: 750 мл (меньше 1500) -> сброс в 0
        addWater(3)
        composeTestRule.onNodeWithTag(WaterTags.FINISH).performClick()
        composeTestRule.onNodeWithTag(WaterTags.STREAK).assertTextEquals("Дней подряд: 0")
    }
}
