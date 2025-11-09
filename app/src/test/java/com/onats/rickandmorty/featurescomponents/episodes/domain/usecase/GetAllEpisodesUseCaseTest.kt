package com.onats.rickandmorty.featurescomponents.episodes.domain.usecase

import androidx.paging.testing.asSnapshot
import com.google.common.truth.Truth.assertThat
import com.onats.rickandmorty.featurescomponents.episodes.data.fakes.FakeEpisodeRepositoryImpl
import com.onats.rickandmorty.featurescomponents.episodes.domain.EpisodesRepository
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class GetAllEpisodesUseCaseTest {

    private lateinit var episodesRepository: EpisodesRepository
    private lateinit var getAllEpisodesUseCase: GetAllEpisodesUseCase

    @Before
    fun setup() {
        episodesRepository = FakeEpisodeRepositoryImpl()
        getAllEpisodesUseCase = GetAllEpisodesUseCase(episodesRepository)
    }

    @Test
    fun `invoke returns flow of paginated episodes`() = runTest {
        val result = getAllEpisodesUseCase.invoke()
        val pagingData = result.asSnapshot()
        assertThat(pagingData).isNotEmpty()
        assertThat(pagingData.first().id).isEqualTo(1)
    }
}