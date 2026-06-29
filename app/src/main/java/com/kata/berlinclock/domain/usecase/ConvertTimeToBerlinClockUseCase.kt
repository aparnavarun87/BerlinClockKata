package com.kata.berlinclock.domain.usecase

import com.kata.berlinclock.domain.model.BerlinClockState

class ConvertTimeToBerlinClockUseCase {
    fun execute(time: String): BerlinClockState {
        val seconds = parseTime(time)

        return BerlinClockState(
            secondsRow = getSecondsRow(seconds)
        )
    }

    private fun parseTime(time: String): Int {
        val parts = time.split(":")
        return parts[2].toInt()
    }

    private fun getSecondsRow(seconds: Int): String {
        return if (seconds % 2 != 0) "O" else "Y"
    }
}