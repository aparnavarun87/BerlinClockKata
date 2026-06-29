package com.kata.berlinclock.presentation

import com.kata.berlinclock.domain.model.BerlinClockState

sealed class BerlinClockUiState {
    object Idle : BerlinClockUiState()
    object Loading : BerlinClockUiState()
    data class Success(val berlinClockState: BerlinClockState) : BerlinClockUiState()
    data class Error(val message: String) : BerlinClockUiState()
}