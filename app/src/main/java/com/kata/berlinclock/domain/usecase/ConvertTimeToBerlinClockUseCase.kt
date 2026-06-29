package com.kata.berlinclock.domain.usecase

import com.kata.berlinclock.domain.model.BerlinClockState
import javax.inject.Inject

class ConvertTimeToBerlinClockUseCase @Inject constructor() {
    fun execute(time: String): BerlinClockState {
        val (hours, minutes, seconds) = parseTime(time)

        return BerlinClockState(
            secondsRow = getSecondsRow(seconds),
            fiveHourRow = getFiveHourRow(hours),
            oneHourRow = getOneHourRow(hours),
            fiveMinuteRow = getFiveMinuteRow(minutes),
            oneMinuteRow = getOneMinuteRow(minutes)
        )
    }

    private fun parseTime(time: String): Triple<Int, Int, Int> {
        val parts = time.split(":")
        return Triple(
            parts[0].toInt(),
            parts[1].toInt(),
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

    private fun getOneHourRow(hours: Int): String {
        val oneHourCount = hours % 5
        val row = StringBuilder()
        repeat(4) { i ->
            row.append(if (i < oneHourCount) "R" else "O")
        }
        return row.toString()
    }

    private fun getFiveMinuteRow(minutes: Int): String {
        val fiveMinuteCount = minutes / 5
        val row = StringBuilder()

        repeat(11) { i ->
            when {
                i >= fiveMinuteCount -> row.append("O")
                (i + 1) % 3 == 0 -> row.append("R")  // Denote Red for 15, 30, 45 minutes
                else -> row.append("Y")
            }
        }
        return row.toString()
    }

    private fun getOneMinuteRow(minutes: Int): String {
        val oneMinuteCount = minutes % 5
        val row = StringBuilder()
        repeat(4) { i ->
            row.append(if (i < oneMinuteCount) "Y" else "O")
        }
        return row.toString()
    }
}