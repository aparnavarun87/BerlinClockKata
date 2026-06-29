package com.kata.berlinclock.domain.model

data class BerlinClockState(
    val secondsRow: String,      // Row 1: Yellow blink (Y / O)
    val fiveHourRow: String,     // Row 2: 4 Red fields
    val oneHourRow: String,     // Row 3: 4 Red fields
    val fiveMinuteRow: String,   // Row 4: 11 Yellow/Red fields
    val oneMinuteRow: String     // Row 5: 4 Yellow fields
){
    fun toDisplayString(): String {
        return listOf(
            secondsRow,
            fiveHourRow,
            oneHourRow,
            fiveMinuteRow,
            oneMinuteRow
        ).joinToString("\n")
    }
}