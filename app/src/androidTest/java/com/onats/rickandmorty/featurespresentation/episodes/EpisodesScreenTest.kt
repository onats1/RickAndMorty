package com.onats.rickandmorty.featurespresentation.episodes

import androidx.activity.compose.setContent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.onats.rickandmorty.app.MainActivity
import com.onats.rickandmorty.app.ui.theme.RickAndMortyTheme
import com.onats.rickandmorty.app.utils.TestTags
import com.onats.rickandmorty.di.EpisodesModule
import com.onats.rickandmorty.featurescomponents.episodes.data.remote.models.toEpisode
import com.onats.rickandmorty.featurescomponents.episodes.domain.models.Episode
import com.onats.rickandmorty.featurespresentation.episodes.models.fakeEpisodesDto
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
@UninstallModules(EpisodesModule::class)
class EpisodesScreenTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mockWebServer: MockWebServer
    private val successJson = "get_all_episodes_response.json"

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
    fun test_episodes_screen_displays_lazy_column() {
        val jsonResponse = MockResponseFileReader(successJson).content
        mockWebServer.enqueue(MockResponse().setBody(jsonResponse).setResponseCode(200))
        composeTestRule.activity.setContent {
            RickAndMortyTheme {
                EpisodesScreen()
            }
        }
        composeTestRule.onNode(hasScrollAction()).assertExists()
    }

    @Test
    fun test_episodes_screen_is_scrollable_correctly() = runTest {
        val episodes = fakeEpisodesDto.map { it.toEpisode() }
        setEpisodeScreenContent(flowOf(PagingData.from(episodes)))

        composeTestRule.onNodeWithTag(TestTags.EPISODES_SCREEN_LAZY_COLUMN)
            .performScrollToIndex(episodes.lastIndex)
        composeTestRule.awaitIdle()
        composeTestRule.onNodeWithText(episodes.last().name).assertIsDisplayed()
    }

    private fun setEpisodeScreenContent(episodes: Flow<PagingData<Episode>>) {
        composeTestRule.activity.setContent {
            EpisodesScreen(
                episodes = episodes.collectAsLazyPagingItems(),
            )
        }
    }
}