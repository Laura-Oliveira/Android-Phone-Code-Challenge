package com.navigation

import com.model.Song

interface NavigatorInterface {
    fun onNavigateToHome()
    fun onNavigateToPlayer(song: Song)
    fun onNavigateToAlbum()
}