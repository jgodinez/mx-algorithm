package com.algorithms.home

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDestination
import androidx.navigation.fragment.findNavController

fun Fragment.navigate(@IdRes actionId: Int) {
    if (!isAlreadyAtDestination(actionId)) {
        findNavController().navigate(actionId)
    }
}

private fun Fragment.isAlreadyAtDestination(@IdRes actionId: Int): Boolean {
    val previousDestinationId = previousDestination?.getDestinationIdFromAction(actionId)
    val currentDestinationId = currentDestination()?.id
    return previousDestinationId == currentDestinationId
}

private fun NavDestination.getDestinationIdFromAction(@IdRes actionId: Int) =
    getAction(actionId)?.destinationId

fun Fragment.currentDestination() = findNavController().currentDestination

val Fragment.previousDestination: NavDestination?
    get() = findNavController().previousBackStackEntry?.destination

fun Fragment.onBackPressed() = requireActivity().onBackPressedDispatcher.onBackPressed()