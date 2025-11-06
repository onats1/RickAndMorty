package com.onats.rickandmorty.featurespresentation.characters

import androidx.activity.compose.setContent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToIndex
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.onats.rickandmorty.app.MainActivity
import com.onats.rickandmorty.app.ui.theme.RickAndMortyTheme
import com.onats.rickandmorty.app.utils.TestTags
import com.onats.rickandmorty.di.CharactersModule
import com.onats.rickandmorty.featurescomponents.characters.data.remote.models.toCharacter
import com.onats.rickandmorty.featurescomponents.characters.domain.models.Character
import com.onats.rickandmorty.featurespresentation.characters.models.fakeCharactersDto
import com.onats.rickandmorty.utils.MockResponseFileReader
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CharactersModule::class)
class CharactersScreenTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mockWebServer: MockWebServer
    private val successJson = "get_all_characters_response.json"

    @Before
    fun setup() {
        hiltRule.inject()
        mockWebServer = MockWebServer()
        mockWebServer.start(8080)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun test_characters_screen_displays_lazy_column() {
        val jsonResponse = MockResponseFileReader(successJson).content
        mockWebServer.enqueue(MockResponse().setBody(jsonResponse).setResponseCode(200))
        composeTestRule.activity.setContent {
            RickAndMortyTheme {
                CharactersScreen()
            }
        }
        composeTestRule.onNode(hasScrollAction()).assertExists()
    }

    @Test
    fun test_characters_screen_is_scrollable_correctly() = runTest {
        val characters = fakeCharactersDto.map { it.toCharacter() }

        setCharacterScreenContent(flowOf(PagingData.from(characters)))

        composeTestRule.onNodeWithTag(TestTags.CHARACTERS_SCREEN_LAZY_COLUMN)
            .performScrollToIndex(characters.lastIndex)
        composeTestRule.awaitIdle()
        composeTestRule.onNodeWithText(characters.last().name).assertIsDisplayed()

    }

    private fun setCharacterScreenContent(characters: Flow<PagingData<Character>>) {
        composeTestRule.activity.setContent {
            CharactersScreen(
                characters = characters.collectAsLazyPagingItems(),
            )
        }
    }

}