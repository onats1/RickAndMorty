package com.onats.rickandmorty.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.onats.rickandmorty.app.bottomnavbar.BottomNavBar
import com.onats.rickandmorty.app.bottomnavbar.models.NavigationRoutes
import com.onats.rickandmorty.app.utils.TestTags
import com.onats.rickandmorty.featurespresentation.characters.CharactersScreen
import com.onats.rickandmorty.featurespresentation.episodes.EpisodesScreen

@Composable
fun MainScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(
                navController,
                modifier = Modifier.testTag(TestTags.BOTTOM_NAV_BAR)
            )
        }
    ) { innerPadding ->

        val graph =
            navController.createGraph(startDestination = NavigationRoutes.Characters.route) {
                composable(NavigationRoutes.Characters.route) {
                    CharactersScreen(modifier = Modifier.testTag(TestTags.CHARACTERS_SCREEN))
                }
                composable(NavigationRoutes.Episodes.route) {
                    EpisodesScreen(modifier = Modifier.testTag(TestTags.EPISODES_SCREEN))
                }
            }

        NavHost(
            navController = navController,
            graph = graph,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen(navController = rememberNavController())
}