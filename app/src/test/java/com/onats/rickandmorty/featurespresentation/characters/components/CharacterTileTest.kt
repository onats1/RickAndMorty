package com.onats.rickandmorty.featurespresentation.characters.components

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.onats.rickandmorty.featurescomponents.characters.domain.models.Character
import com.onats.rickandmorty.featurespresentation.characters.CharactersTestTags
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CharacterTileTest {


    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        val character = Character(
            name = "Rick Sanchez",
            species = "Human",
            status = "Alive",
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            id = 1,
            gender = "Male",
            url = "https://rickandmortyapi.com/api/character/1",
            type = ""
        )
        composeTestRule.setContent {
            CharacterTile(character = character) { }
        }
    }

    @Test
    fun `test that image is displayed`() {
        composeTestRule.onNodeWithTag(CharactersTestTags.CHARACTER_TILE_IMAGE, useUnmergedTree = true)
            .assertExists()
    }

    @Test
    fun `test that character tile displays correct name`() {
        composeTestRule.onNodeWithTag(CharactersTestTags.CHARACTER_TILE_NAME, useUnmergedTree = true)
            .assertExists()
        composeTestRule.onNodeWithTag(CharactersTestTags.CHARACTER_TILE_NAME, useUnmergedTree = true)
            .assertTextEquals("Rick Sanchez")
    }

    @Test
    fun `test that character tile displays correct species`() {
        composeTestRule.onNodeWithTag(CharactersTestTags.CHARACTER_TILE_SPECIES, useUnmergedTree = true)
            .assertExists()
        composeTestRule.onNodeWithTag(CharactersTestTags.CHARACTER_TILE_SPECIES, useUnmergedTree = true)
            .assertTextEquals("Human")
    }

    @Test
    fun `test that character tile displays correct status`() {
        composeTestRule.onNodeWithTag(CharactersTestTags.CHARACTER_TILE_STATUS, useUnmergedTree = true)
            .assertExists()
        composeTestRule.onNodeWithTag(CharactersTestTags.CHARACTER_TILE_STATUS, useUnmergedTree = true)
            .assertTextEquals("Alive")
    }


}