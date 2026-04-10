package com.navigation

enum class Routes(val route: String) {
    SPLASH("splash"),
    HOME("home"),
    PLAYER("player/{songId}"),
    ALBUM("album")
}