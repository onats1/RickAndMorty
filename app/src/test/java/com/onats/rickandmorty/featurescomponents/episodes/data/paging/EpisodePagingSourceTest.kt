package com.onats.rickandmorty.featurescomponents.episodes.data.paging

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import com.google.common.truth.Truth.assertThat
import com.onats.rickandmorty.featurescomponents.episodes.data.fakes.fakeEpisodesDto
import com.onats.rickandmorty.featurescomponents.episodes.data.fakes.fakeEpisodesResponse
import com.onats.rickandmorty.featurescomponents.episodes.data.remote.EpisodesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class EpisodePagingSourceTest {

    @Mock
    lateinit var mockEpisodesApi: EpisodesApi

    @Test
    fun `load returns page on successful load of data`() = runTest {
        whenever(mockEpisodesApi.getAllEpisodes(1)).thenReturn(Response.success(fakeEpisodesResponse))
        val pagingSource = EpisodePagingSource(mockEpisodesApi)

        val pager = TestPager(
            pagingSource = pagingSource,
            config = PagingConfig(pageSize = 10)
        )

        val episodes = fakeEpisodesDto

        val result = pager.refresh() as PagingSource.LoadResult.Page

        //Verify that getAllCharacters was called
        verify(mockEpisodesApi).getAllEpisodes(1)

        assertThat(result.data)
            .containsExactlyElementsIn(episodes)
            .inOrder()
    }

    @Test
    fun `load returns error on error loading data`() = runTest {
        whenever(mockEpisodesApi.getAllEpisodes(1)).thenReturn(Response.error(404, "".toResponseBody()))
        val pagingSource = EpisodePagingSource(mockEpisodesApi)
        val pager = TestPager(
            pagingSource = pagingSource,
            config = PagingConfig(pageSize = 10)
        )

        val result = pager.refresh()

        //Verify that getAllCharacters was called
        verify(mockEpisodesApi).getAllEpisodes(1)
        assertThat(result).isInstanceOf(PagingSource.LoadResult.Error::class.java)
    }

    @Test
    fun `load returns error when network api throws exception`() = runTest {
        whenever(mockEpisodesApi.getAllEpisodes(1)).thenThrow(RuntimeException::class.java)

        val pagingSource = EpisodePagingSource(mockEpisodesApi)
        val pager = TestPager(
            pagingSource = pagingSource,
            config = PagingConfig(pageSize = 10)
        )

        val result = pager.refresh()

        //Verify that getAllCharacters was called
        verify(mockEpisodesApi).getAllEpisodes(1)
        assertThat(result).isInstanceOf(PagingSource.LoadResult.Error::class.java)
    }
}