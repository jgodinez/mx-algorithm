package com.algorithms.challenge.balanced

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

internal const val VALID = "valid"
internal const val INVALID = "invalid"

internal class BalancedParenthesesViewModel : ViewModel() {

    private val _uiState = MutableLiveData<BalancedUiState>()
    val uiState: LiveData<BalancedUiState> = _uiState

    fun checkBalanced(input: String?) {
        _uiState.value = BalancedUiState.Loading
        val sanitizedInput = input?.replace("[^()]".toRegex(), "")
        val outputValue: String = if (isBalanced(sanitizedInput)) VALID else INVALID
        _uiState.value = BalancedUiState.Success(outputValue)
        println(
            """
            input: $input
            sanitized input: $sanitizedInput
            output value: $outputValue
            -------------------------------------
            """
        )
    }

    private fun isBalanced(input: String?): Boolean {
        if (input.isNullOrEmpty()) {
            return true
        }
        var openCharCounter = 0
        for (char in input) {
            if (char == '(') {
                openCharCounter++
            } else if (openCharCounter > 0) {
                openCharCounter--
            } else {
                return false
            }
        }
        return openCharCounter == 0
    }
}

internal sealed interface BalancedUiState {
    data object Loading : BalancedUiState
    data class Success(val outputValue: String) : BalancedUiState
}