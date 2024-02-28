package com.algorithms.challenge

internal fun Char.isLineBreak(): Boolean = this == '\n'

internal fun Char.isNumber(): Boolean = this in '0'..'9'

internal fun Char.isUppercaseDigit(): Boolean = this in 'A'..'Z'

internal fun Char.isLowercaseDigit(): Boolean = this in 'a'..'z'

internal fun Int.isEven(): Boolean = this % 2 == 0

internal fun Int.isOdd(): Boolean = this % 2 == 1