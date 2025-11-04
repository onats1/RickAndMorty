package com.onats.rickandmorty.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.onats.rickandmorty.app.bottomnavbar.BottomNavBar
import com.onats.rickandmorty.app.bottomnavbar.models.NavigationRoutes
import com.onats.rickandmorty.featurespresentation.characters.CharactersScreen

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->

        val graph = navController.createGraph(startDestination = NavigationRoutes.Characters.route) {
            composable(NavigationRoutes.Characters.route) {
                CharactersScreen()
            }
            composable(NavigationRoutes.Episodes.route) {
                Box{}
            }
        }

        NavHost(
            navController = navController,
            graph = graph,
            modifier = Modifier.padding(innerPadding)
        )
    }
}