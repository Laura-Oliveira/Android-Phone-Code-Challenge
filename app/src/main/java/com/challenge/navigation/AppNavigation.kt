package com.challenge.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.challenge.SplashScreen
import com.challenge.SplashScreenContent
import songsPlaylist.SongsListScreen
import songsPlaylist.SongsListScreenContent

//import com.playlist.app.ui.splash.SplashScreen
//import com.playlist.feature.playlist.HomeScreen // 🔥 vem do módulo playlist

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {

        composable(Routes.SPLASH) {
            SplashScreenContent(
                onNavigateToHome = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.HOME) {
            SongsListScreen()
        }

        composable(Routes.PLAYER) {
            // LoginScreen()
        }

        composable(Routes.ALBUM) {
            // LoginScreen()
        }
    }
}