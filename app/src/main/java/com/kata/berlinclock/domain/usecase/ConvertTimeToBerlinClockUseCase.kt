package com.kata.berlinclock.domain.usecase

import com.kata.berlinclock.domain.model.BerlinClockState

class ConvertTimeToBerlinClockUseCase {
    fun execute(time: String): BerlinClockState {
        val (hours,seconds) = parseTime(time)

        return BerlinClockState(
            secondsRow = getSecondsRow(seconds),
            fiveHourRow = getFiveHourRow(hours)
        )
    }

    private fun parseTime(time: String): Pair<Int, Int> {
        val parts = time.split(":")
        return Pair(
            parts[0].toInt(),
            parts[2].toInt()
        )
    }

    private fun getSecondsRow(seconds: Int): String {
        return if (seconds % 2 != 0) "O" else "Y"
    }

    private fun getFiveHourRow(hours: Int): String {
        val fiveHourCount = hours / 5
        val row = StringBuilder()
        repeat(4) { i ->
            row.append(if (i < fiveHourCount) "R" else "O")
        }
        return row.toString()
    }
}