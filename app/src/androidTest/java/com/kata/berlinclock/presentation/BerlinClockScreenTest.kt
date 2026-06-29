package com.kata.berlinclock.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Test
import org.junit.Rule

class BerlinClockScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val testUiStateFlow = MutableStateFlow<BerlinClockUiState>(BerlinClockUiState.Idle)

    private fun setupViewModelMock(uiState: BerlinClockUiState) {
        testUiStateFlow.value = uiState
    }

    @Test
    fun testScreenDisplaysTitle() {
        setupViewModelMock(BerlinClockUiState.Idle)

        composeTestRule.setContent {
            BerlinClockScreen()
        }

        composeTestRule.onNodeWithText("Berlin Clock")
            .assertIsDisplayed()
    }

    @Test
    fun testTimeInputFieldIsDisplayed() {
        setupViewModelMock(BerlinClockUiState.Idle)

        composeTestRule.setContent {
            BerlinClockScreen()
        }

        composeTestRule.onNodeWithText("Enter time (HH:MM:SS)")
            .assertIsDisplayed()
    }
}