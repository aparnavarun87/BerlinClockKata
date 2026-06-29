package com.kata.berlinclock.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kata.berlinclock.domain.usecase.ConvertTimeToBerlinClockUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BerlinClockViewModel @Inject constructor(
    private val convertTimeToBerlinClockUseCase: ConvertTimeToBerlinClockUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<BerlinClockUiState>(BerlinClockUiState.Idle)
    val uiState: StateFlow<BerlinClockUiState> = _uiState.asStateFlow()

    fun convertTime(time: String) {
        viewModelScope.launch {
            if(time.isNotEmpty()){
                _uiState.value = BerlinClockUiState.Loading

                val berlinClockState = convertTimeToBerlinClockUseCase.execute(time)
                _uiState.value = BerlinClockUiState.Success(berlinClockState)
            }
        }
    }
}