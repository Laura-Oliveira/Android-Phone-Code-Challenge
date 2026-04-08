package com.core.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.core.SplashScreenContent
import songsPlaylist.view.SongsScreen

@Composable
fun AppNavigation()
{
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
            SongsScreen()
        }

        composable(Routes.PLAYER) {
            // LoginScreen()
        }

        composable(Routes.ALBUM) {
            // LoginScreen()
        }
    }
}