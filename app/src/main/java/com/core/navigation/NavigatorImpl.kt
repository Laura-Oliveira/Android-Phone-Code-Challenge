package com.core.navigation

import androidx.navigation.NavHostController
import jakarta.inject.Inject
import songs.repository.SongsRepository

class NavigatorImpl @Inject constructor(
    private val navController: NavHostController,
    private val repository: SongsRepository
) : Navigator {

    override fun onNavigateToHome() {
        navController.navigate(Routes.HOME) {
            popUpTo(Routes.SPLASH) { inclusive = true }
        }
    }

    override fun onNavigateToPlayer() {
        navController.navigate(Routes.PLAYER)
    }

    override fun onNavigateToAlbum() {
        navController.navigate(Routes.ALBUM)
    }
}