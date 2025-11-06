package com.onats.rickandmorty.featurescomponents.episodes.data.remote

import com.google.common.truth.Truth.assertThat
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.onats.rickandmorty.featurescomponents.utils.MainDispatcherRule
import com.onats.rickandmorty.featurescomponents.utils.MockResponseFileReader
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.IOException
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit


class EpisodesApiTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var episodesApi: EpisodesApi
    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }
    private val contentType = "application/json".toMediaType()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        episodesApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(EpisodesApi::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getAllEpisodes should hit correct endpoint`() = runTest {
        mockWebServer.enqueue(MockResponse().setBody("{}").setResponseCode(200))
        val expectedEndpoint = "/api/episode?page=1"

        episodesApi.getAllEpisodes()

        val request = mockWebServer.takeRequest()
        assertThat(request.path).isEqualTo(expectedEndpoint)
    }

    @Test
    fun `getAllEpisodes should return correct response`() = runTest {
        val successJson = MockResponseFileReader("get_all_episodes_response.json").content
        mockWebServer.enqueue(MockResponse().setBody(successJson).setResponseCode(200))

        val response = episodesApi.getAllEpisodes()

        assertThat(response.isSuccessful).isTrue()
        assertThat(response.body()).isNotNull()
        assertThat(response.body()?.info?.count).isEqualTo(51)
        assertThat(response.body()?.results?.first()?.id).isEqualTo(1)
    }

    @Test
    fun `getAllEpisodes should return error response if unsuccessful`() = runTest {
        mockWebServer.enqueue(MockResponse().setResponseCode(500))

        val response = episodesApi.getAllEpisodes()

        assertThat(response.isSuccessful).isFalse()
        assertThat(response.body()).isNull()
        assertThat(response.code()).isEqualTo(500)
    }

    @Test(expected = IOException::class)
    fun `getAllEpisodes should throw exception if connection cannot be established`() = runTest {
        mockWebServer.shutdown()

        episodesApi.getAllEpisodes()
    }

}