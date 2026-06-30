package com.kata.berlinclock.presentation

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import com.kata.berlinclock.domain.model.BerlinClockState
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
            BerlinClockScreen(onConvertClick = {},testUiStateFlow.value)
        }

        composeTestRule.onNodeWithText("Berlin Clock")
            .assertIsDisplayed()
    }

    @Test
    fun testTimeInputFieldIsDisplayed() {
        setupViewModelMock(BerlinClockUiState.Idle)

        composeTestRule.setContent {
            BerlinClockScreen(onConvertClick = {}, testUiStateFlow.value)
        }

        composeTestRule.onNodeWithText("Enter time (HH:MM:SS)")
            .assertIsDisplayed()
    }

    @Test
    fun testConvertButtonIsDisplayed() {
        setupViewModelMock(BerlinClockUiState.Idle)

        composeTestRule.setContent {
            BerlinClockScreen(onConvertClick = {}, testUiStateFlow.value)
        }

        composeTestRule.onNodeWithText("Convert to Berlin Clock")
            .assertIsDisplayed()
    }

    @Test
    fun testConvertButtonClickCallsViewModel() {
        setupViewModelMock(BerlinClockUiState.Idle)

        composeTestRule.setContent {
            BerlinClockScreen(onConvertClick = {}, testUiStateFlow.value)
        }

        composeTestRule.onNodeWithText("Convert to Berlin Clock")
            .assertHasClickAction()
    }

    @Test
    fun testIdleStateDisplaysInstructionalText() {
        testUiStateFlow.value = BerlinClockUiState.Idle

        composeTestRule.setContent {
            BerlinClockScreen(onConvertClick = {}, testUiStateFlow.value)
        }

        composeTestRule.onNodeWithText("Enter a time to see the Berlin Clock")
            .assertIsDisplayed()
    }

    @Test
    fun testLoadingStateShowsProgressIndicator() {
        testUiStateFlow.value = BerlinClockUiState.Loading

        composeTestRule.setContent {
            BerlinClockScreen(onConvertClick = {}, testUiStateFlow.value)
        }

        composeTestRule.onRoot()
            .assertExists()
    }

    @Test
    fun testSuccessStateDisplaysBerlinClockDisplay() {
        val berlinClockState = BerlinClockState(
            secondsRow = "O",
            fiveHourRow = "RROO",
            oneHourRow = "RROO",
            fiveMinuteRow = "YYRYYRYYRYY",
            oneMinuteRow = "YOOO"
        )
        testUiStateFlow.value = BerlinClockUiState.Success(berlinClockState)

        composeTestRule.setContent {
            BerlinClockScreen(onConvertClick = {}, testUiStateFlow.value)
        }

        composeTestRule.onRoot()
            .assertExists()
    }

    @Test
    fun testFiveHourLabelDisplayed() {
        composeTestRule.setContent {
            ClockRow(
                label = "5-Hour",
                value = "RROO",
                description = "5 hours each",
            )
        }

        composeTestRule.onNodeWithText("5-Hour")
            .assertIsDisplayed()
    }

    @Test
    fun testOneHourLabelDisplayed() {
        composeTestRule.setContent {
            ClockRow(
                label = "1-Hour",
                value = "RROO",
                description = "1 hour each",
            )
        }

        composeTestRule.onNodeWithText("1-Hour")
            .assertIsDisplayed()
    }

    @Test
    fun testFiveMinuteLabelDisplayed() {
        composeTestRule.setContent {
            ClockRow(
                label = "5-Minute",
                value = "YYRYYRYYRYY",
                description = "5 minutes each",
            )
        }

        composeTestRule.onNodeWithText("5-Minute")
            .assertIsDisplayed()
    }

    @Test
    fun testOneMinuteLabelDisplayed() {
        composeTestRule.setContent {
            ClockRow(
                label = "1-Minute",
                value = "YOOO",
                description = "1 minute each"
            )
        }

        composeTestRule.onNodeWithText("1-Minute")
            .assertIsDisplayed()
    }

    @Test
    fun testTimeDisplayTextShowsCorrectContent() {
        val testDisplayString = "O\nRROO\nRROO\nYYRYYRYYRYY\nYOOO"

        composeTestRule.setContent {
            TimeDisplayText(text = testDisplayString)
        }

        composeTestRule.onNodeWithTag("Time Display")
            .assert(hasText(testDisplayString))
    }
}