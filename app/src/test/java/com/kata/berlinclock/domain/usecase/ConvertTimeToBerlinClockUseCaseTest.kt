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
}