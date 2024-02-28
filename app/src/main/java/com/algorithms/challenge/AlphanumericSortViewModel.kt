package com.algorithms.challenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

internal class AlphanumericSortViewModel : ViewModel() {

    private val _uiState = MutableLiveData<SortUiState>()
    val uiState: LiveData<SortUiState> = _uiState

    fun sort(input: String, trimLineBreaks: Boolean) {
        _uiState.value = SortUiState.Loading
        val sortedChars = SortedChars()
        for (char in input) {
            when {
                char.isLineBreak() -> if (trimLineBreaks.not()) sortedChars.addOther(char)
                char.isNumber() -> sortedChars.addNumber(char)
                char.isUppercaseDigit() -> sortedChars.addUpperCase(char)
                char.isLowercaseDigit() -> sortedChars.addLowerCase(char)
                else -> sortedChars.addOther(char)
            }
        }
        _uiState.value = SortUiState.Success(sortedChars.getSortedChars())
    }

    inner class SortedChars {
        private val evenNumber = mutableListOf<Char>()
        private val oddNumber = mutableListOf<Char>()
        private val upperCase = mutableListOf<Char>()
        private val lowerCase = mutableListOf<Char>()
        private val other = mutableListOf<Char>()

        fun addNumber(char: Char) {
            if (char.digitToInt().isEven()) {
                evenNumber.add(char)
            } else {
                oddNumber.add(char)
            }
        }

        fun addUpperCase(char: Char) {
            upperCase.add(char)
        }

        fun addLowerCase(char: Char) {
            lowerCase.add(char)
        }

        fun addOther(char: Char) {
            other.add(char)
        }

        fun getSortedChars(): String {
            lowerCase.sort()
            upperCase.sort()
            oddNumber.sort()
            evenNumber.sort()
            other.sort()
            return ArrayList<Char>().apply {
                addAll(lowerCase)
                addAll(upperCase)
                addAll(oddNumber)
                addAll(evenNumber)
                addAll(other)
            }.joinToString("")
        }

        override fun toString(): String {
            return """
                lowerCase (${lowerCase.size}): $lowerCase
                upperCase (${upperCase.size}): $upperCase
                oddNumber (${oddNumber.size}): $oddNumber
                evenNumber (${evenNumber.size}): $evenNumber
                other (${other.size}): $other
            """
        }
    }
}

internal sealed interface SortUiState {
    data object Loading : SortUiState
    data class Success(val sortedString: String) : SortUiState
}