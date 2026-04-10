package com.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(
    splashContent: @Composable (onFinished: () -> Unit) -> Unit,
    featureGraphs: List<FeatureGraph>
) {
    val navController = rememberNavController()

    val navigator = remember(navController) {
        NavigatorInterfaceImpl(navController)
    }

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH.route
    ) {

        composable(Routes.SPLASH.route) { backStackEntry ->
            splashContent {
                navigator.onNavigateToHome()
            }
        }

        featureGraphs.forEach { graph ->
            graph.registerGraph(this, navigator, navController)
        }
    }
}