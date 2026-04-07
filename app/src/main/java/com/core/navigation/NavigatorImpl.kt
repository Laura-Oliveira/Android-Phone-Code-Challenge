package com.core.navigation

import androidx.navigation.NavHostController

class NavigatorImpl(
    private val navController: NavHostController
) : Navigator {

    override fun onNavigateToHome() {
        navController.navigate(Routes.HOME) {
            popUpTo(Routes.SPLASH) { inclusive = true }
        }
    }

    override fun onNavigateToPlayer() {
        TODO("Not yet implemented")
    }

    override fun onNavigateToAlbum() {
        TODO("Not yet implemented")
    }
}