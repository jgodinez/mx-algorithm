package com.algorithms.challenge.balanced

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class BalancedParenthesesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val viewModel = BalancedParenthesesViewModel()

    @Test
    fun `Given null value, the displayed value should be valid`() {
        val input: String? = null
        viewModel.checkBalanced(input)
        assertEquals(BalancedUiState.Success(VALID), viewModel.uiState.value)
    }

    @Test
    fun `Given a string value with an empty string, the displayed value should be valid`() {
        val input = ""
        viewModel.checkBalanced(input)
        assertEquals(BalancedUiState.Success(VALID), viewModel.uiState.value)
    }

    @Test
    fun `Given a string with a sequence of unmatched parentheses, the displayed value must be valid invalid`() {
        val parentheses = arrayOf(")(", ")()(", "())(()", "(Hello (,) world (!)))")
        for (input in parentheses) {
            viewModel.checkBalanced(input)
            assertEquals(BalancedUiState.Success(INVALID), viewModel.uiState.value)
        }
    }

    @Test
    fun `Given a string with a sequence of matched parentheses, the displayed value must be valid valid`() {
        val parentheses =
            arrayOf("()", "(hello, world)", "text (as this) is ok ()", "(Hello (,) world (!))")
        for (input in parentheses) {
            viewModel.checkBalanced(input)
            assertEquals(BalancedUiState.Success(VALID), viewModel.uiState.value)
        }
    }
}