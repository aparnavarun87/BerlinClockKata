package com.kata.berlinclock.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kata.berlinclock.domain.usecase.ConvertTimeToBerlinClockUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BerlinClockViewModel @Inject constructor(
    private val convertTimeToBerlinClockUseCase: ConvertTimeToBerlinClockUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<BerlinClockUiState>(BerlinClockUiState.Idle)
    val uiState: StateFlow<BerlinClockUiState> = _uiState.asStateFlow()

    fun convertTime(time: String) {
        viewModelScope.launch {
            if(time.isNotEmpty()){
                _uiState.value = BerlinClockUiState.Loading

                if (!isValidTimeFormat(time)) {
                    _uiState.value = BerlinClockUiState.Error("Invalid time format. Use HH:MM:SS")
                    return@launch
                }

                val berlinClockState = convertTimeToBerlinClockUseCase.execute(time)
                _uiState.value = BerlinClockUiState.Success(berlinClockState)
            }
        }
    }

    private fun isValidTimeFormat(time: String): Boolean {
        val parts = time.split(":")
        if (parts.size != 3) return false

        val hours = parts[0].toInt()
        val minutes = parts[1].toInt()
        val seconds = parts[2].toInt()
        return hours in 0..23 && minutes in 0..59 && seconds in 0..59
    }
}