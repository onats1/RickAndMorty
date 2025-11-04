package com.onats.rickandmorty.app.bottomnavbar.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
)

val navigationItems = listOf(
    NavigationItem(
        title = "Characters",
        icon = Icons.Filled.Person,
        route = NavigationRoutes.Characters.route
    ),
    NavigationItem(
        title = "Episodes",
        icon = Icons.Filled.List,
        route = NavigationRoutes.Episodes.route
    )
)