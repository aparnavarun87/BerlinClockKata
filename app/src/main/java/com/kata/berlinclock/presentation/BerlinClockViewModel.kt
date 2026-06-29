package com.kata.berlinclock.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BerlinClockViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<BerlinClockUiState>(BerlinClockUiState.Idle)
    val uiState: StateFlow<BerlinClockUiState> = _uiState.asStateFlow()
}