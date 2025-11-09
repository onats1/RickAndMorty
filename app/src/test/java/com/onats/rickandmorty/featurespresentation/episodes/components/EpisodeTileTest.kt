package com.onats.rickandmorty.featurespresentation.episodes.components

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.onats.rickandmorty.featurescomponents.episodes.domain.models.Episode
import com.onats.rickandmorty.featurespresentation.episodes.EpisodesTestTags
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class EpisodeTileTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        val episode = Episode(
            airDate = "January 20, 2014",
            characters = listOf(
                "https://rickandmortyapi.com/api/character/6",
                "https://rickandmortyapi.com/api/character/8"
            ),
            created = "2017-11-10T12:56:34.236Z",
            episode = "S01E05",
            id = 5,
            name = "Meeseeks and Destroy",
            url = "https://rickandmortyapi.com/api/episode/5"
        )
        composeTestRule.setContent {
            EpisodeTile(episode = episode) { }
        }
    }

    @Test
    fun `test that episode tile displays correct name`() {
        composeTestRule.onNodeWithTag(EpisodesTestTags.EPISODE_TILE_TITLE, useUnmergedTree = true)
            .assertExists()
        composeTestRule.onNodeWithTag(EpisodesTestTags.EPISODE_TILE_TITLE, useUnmergedTree = true)
            .assertTextEquals("Meeseeks and Destroy")
    }

    @Test
    fun `test that episode tile displays correct air date`() {
        composeTestRule.onNodeWithTag(EpisodesTestTags.EPISODE_AIR_DATE, useUnmergedTree = true)
            .assertExists()
        composeTestRule.onNodeWithTag(EpisodesTestTags.EPISODE_AIR_DATE, useUnmergedTree = true)
            .assertTextEquals("Aired: January 20, 2014")
    }
}