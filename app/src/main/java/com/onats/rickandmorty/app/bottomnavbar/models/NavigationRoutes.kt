package com.onats.rickandmorty.app.bottomnavbar.models

sealed class NavigationRoutes(val route: String) {
    object Characters: NavigationRoutes("characters")
    object Episodes: NavigationRoutes("episodes")
}