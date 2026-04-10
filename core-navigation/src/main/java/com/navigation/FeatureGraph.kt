package com.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

fun interface FeatureGraph {
    fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navigatorInterface: NavigatorInterface,
        navController: NavController
    )
}