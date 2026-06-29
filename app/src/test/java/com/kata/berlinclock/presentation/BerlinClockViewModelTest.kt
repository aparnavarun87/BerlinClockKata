package com.kata.berlinclock.presentation

import com.kata.berlinclock.domain.model.BerlinClockState
import com.kata.berlinclock.domain.usecase.ConvertTimeToBerlinClockUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BerlinClockViewModelTest {

    private lateinit var viewModel: BerlinClockViewModel
    private lateinit var mockUseCase: ConvertTimeToBerlinClockUseCase
    private val testDispatcher = StandardTestDispatcher()

    @BeforeEach
    fun setUp() {
        mockUseCase = mockk()
        Dispatchers.setMain(testDispatcher)
        viewModel = BerlinClockViewModel(mockUseCase)
    }

    @Test
    fun `Should initialize with Idle state` () {
        Assertions.assertEquals(BerlinClockUiState.Idle, viewModel.uiState.value)
    }

    @Test
    fun `Should emit Success state with correct Berlin Clock state`() = runTest {
        val mockState = BerlinClockState(
            secondsRow = "O",
            fiveHourRow = "RROO",
            oneHourRow = "RROO",
            fiveMinuteRow = "YYRYYRYYRYY",
            oneMinuteRow = "YOOO"
        )

        coEvery { mockUseCase.execute("12:56:01")} returns mockState

        viewModel.convertTime("12:56:01")
        advanceUntilIdle()

        val state = viewModel.uiState.value as BerlinClockUiState.Success
        Assertions.assertEquals(mockState, state.berlinClockState)
    }

    @Test
    fun `Should emit Error state for invalid time format`() = runTest {
        viewModel.convertTime("invalid")
        advanceUntilIdle()

        val state = viewModel.uiState.value
        Assertions.assertTrue((state as BerlinClockUiState.Error).message.contains("Invalid"))
    }
}