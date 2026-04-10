package com.navigation

import androidx.navigation.NavController
import com.model.Song

class NavigatorInterfaceImpl(
    private val navController: NavController
) : NavigatorInterface {

    override fun onNavigateToHome() {
        navController.navigate(Routes.HOME.route) {
            popUpTo(Routes.SPLASH.route) { inclusive = true }
        }
    }

    override fun onNavigateToPlayer(song: Song) {
        navController.currentBackStackEntry
            ?.savedStateHandle
            ?.set("song", song)
        navController.navigate(Routes.PLAYER.route)
    }

    override fun onNavigateToAlbum() {
        navController.navigate(Routes.ALBUM.route)
    }
}