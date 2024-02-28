package com.algorithms.home

import androidx.annotation.ColorRes
import androidx.annotation.IdRes

internal class Challenge(
    val content: String,
    @ColorRes val backgroundColor: Int,
    @IdRes val launchResource: Int? = null,
)