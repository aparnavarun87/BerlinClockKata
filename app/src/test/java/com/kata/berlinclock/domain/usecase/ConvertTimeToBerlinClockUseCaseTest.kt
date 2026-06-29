package com.kata.berlinclock.domain.usecase

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName

class ConvertTimeToBerlinClockUseCaseTest {

    private lateinit var useCase: ConvertTimeToBerlinClockUseCase

    @BeforeEach
    fun setUp() {
        useCase = ConvertTimeToBerlinClockUseCase()
    }

    @Test
    @DisplayName("Should return O from Berlin Clock state for 12:56:01")
    fun testTime12_56_01() {
        // Arrange
        val time = "12:56:01"

        // Act
        val result = useCase.execute(time)

        // Assert
        Assertions.assertEquals("O", result.secondsRow)
    }

    @Test
    @DisplayName("Should return Y from Berlin Clock state for 12:56:02")
    fun testTime12_56_02() {
        // Arrange
        val time = "12:56:02"

        // Act
        val result = useCase.execute(time)

        // Assert
        Assertions.assertEquals("Y", result.secondsRow)
    }

    @Test
    @DisplayName("Should return R if five full hours each otherwise O for 11:56:01")
    fun testTime11_56_01() {
        // Arrange
        val time = "11:56:01"

        // Arrange & Act
        val result = useCase.execute(time)

        // Assert
        Assertions.assertEquals("RROO", result.fiveHourRow)
    }

    @Test
    @DisplayName("Should return R if one full hours each otherwise O for 11:56:02")
    fun testTime11_56_02() {
        // Arrange
        val time = "11:56:02"

        // Arrange & Act
        val result = useCase.execute(time)

        // Assert
        Assertions.assertEquals("ROOO", result.oneHourRow)
    }

    @Test
    @DisplayName("Should return R if five full minutes each or 15 30 and 45 minutes past, Y if less than 5 minutes otherwise Y for 12:56:04")
    fun testTime12_56_04() {
        // Arrange
        val time = "12:56:04"

        // Arrange & Act
        val result = useCase.execute(time)

        // Assert
        Assertions.assertEquals("YYRYYRYYRYY", result.fiveMinuteRow)
    }

    @Test
    @DisplayName("Should return Y if one full minute each otherwise O for 12:56:05")
    fun testTime12_56_05() {
        // Arrange
        val time = "12:56:05"

        // Arrange & Act
        val result = useCase.execute(time)

        // Assert
        Assertions.assertEquals("YOOO", result.oneMinuteRow)
    }
}