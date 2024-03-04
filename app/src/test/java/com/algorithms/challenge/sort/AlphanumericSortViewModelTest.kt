package com.algorithms.challenge.sort

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class AlphanumericSortViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val viewModel = AlphanumericSortViewModel()

    @Test
    fun `Given number and letter values, the number values must be displayed after the letter values`() {
        val input = "10a29z38b47y56"
        val output = "abyz1357902468"
        viewModel.sort(input, true)
        assertEquals(SortUiState.Success(output), viewModel.uiState.value)
    }

    @Test
    fun `Given uppercase and lowercase values, the uppercase values must be displayed after the lowercase values`() {
        val input = "aZbYqwErty"
        val output = "abqrtwyEYZ"
        viewModel.sort(input, true)
        assertEquals(SortUiState.Success(output), viewModel.uiState.value)
    }

    @Test
    fun `Given even and odd values, the odd values must be displayed before the even values`() {
        val input = "1029384756"
        val output = "1357902468"
        viewModel.sort(input, true)
        assertEquals(SortUiState.Success(output), viewModel.uiState.value)
    }

    @Test
    fun `Given special values that are not letters or numbers, the values must be displayed after the letters and numbers`() {
        val input = "aZ?bY~10Ö29"
        val output = "abYZ1902?~Ö"
        viewModel.sort(input, true)
        assertEquals(SortUiState.Success(output), viewModel.uiState.value)
    }

    @Test
    fun `Given string values of letters, numbers or any other character, the values must be displayed in order`() {
        val input = "P@rAnar1cu+iRImïcü4r0"
        val output = "accimnrrruAIPR104+@ïü"
        viewModel.sort(input, true)
        assertEquals(SortUiState.Success(output), viewModel.uiState.value)
    }

    @Test
    fun `Given values of a string with a line break, the values must be displayed in order and without a line break`() {
        val input = "P@rAnar1cu+i\nRImïcü4r0"
        val output = "accimnrrruAIPR104+@ïü"
        viewModel.sort(input, true)
        assertEquals(SortUiState.Success(output), viewModel.uiState.value)
    }

    @Test
    fun `Given values of a string with a line break, the values must be displayed in order and with a line break`() {
        val input = "P@rAnar1cu+i\nRImïcü4r0"
        val output = "accimnrrruAIPR104\n+@ïü"
        viewModel.sort(input, false)
        assertEquals(SortUiState.Success(output), viewModel.uiState.value)
    }
}