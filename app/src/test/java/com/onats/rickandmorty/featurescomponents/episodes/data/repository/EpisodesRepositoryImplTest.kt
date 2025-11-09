package com.onats.rickandmorty.featurescomponents.episodes.data.repository

import androidx.paging.testing.asSnapshot
import com.google.common.truth.Truth.assertThat
import com.onats.rickandmorty.featurescomponents.episodes.data.fakes.fakeEpisodesDto
import com.onats.rickandmorty.featurescomponents.episodes.data.fakes.fakeEpisodesResponse
import com.onats.rickandmorty.featurescomponents.episodes.data.paging.EpisodePagingSource
import com.onats.rickandmorty.featurescomponents.episodes.data.remote.EpisodesApi
import com.onats.rickandmorty.featurescomponents.episodes.data.remote.models.toEpisode
import com.onats.rickandmorty.featurescomponents.episodes.domain.EpisodesRepository
import com.onats.rickandmorty.featurescomponents.utils.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class EpisodesRepositoryImplTest {

    @Mock
    private lateinit var episodesApi: EpisodesApi

    private lateinit var episodesPagingSource: EpisodePagingSource

    private lateinit var episodeRepository: EpisodesRepository

    @get:Rule
    val mainCoroutineRule = MainDispatcherRule()

    @Before
    fun setup() {
        episodesPagingSource = EpisodePagingSource(episodesApi)
        episodeRepository = EpisodesRepositoryImpl(episodesPagingSource)
    }

    @Test
    fun `test getAllEpisodes returns paginated data with mapped episodes`() = runTest {
        whenever(episodesApi.getAllEpisodes(any())).thenReturn(Response.success(fakeEpisodesResponse))

        val result = episodeRepository.getAllEpisodes()

        val pagingData = result.asSnapshot()

        val episodes = fakeEpisodesDto.map { it.toEpisode() }

        assertThat(pagingData).isNotEmpty()
        assertThat(pagingData.size).isEqualTo(episodes.size)
        assertThat(pagingData).containsExactlyElementsIn(episodes)
    }
}