package com.kata.berlinclock.presentation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BerlinClockViewModelTest {

    private lateinit var viewModel: BerlinClockViewModel

    @BeforeEach
    fun setUp() {
        viewModel = BerlinClockViewModel()
    }

    @Test
    fun `Should initialize with Idle state` () {
        Assertions.assertEquals(BerlinClockUiState.Idle, viewModel.uiState.value)
    }
}