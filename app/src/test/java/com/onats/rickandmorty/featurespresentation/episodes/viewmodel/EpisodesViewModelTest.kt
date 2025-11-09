package com.onats.rickandmorty.featurespresentation.episodes.viewmodel

import androidx.paging.testing.asSnapshot
import com.google.common.truth.Truth.assertThat
import com.onats.rickandmorty.featurescomponents.episodes.data.fakes.FakeEpisodeRepositoryImpl
import com.onats.rickandmorty.featurescomponents.episodes.domain.usecase.GetAllEpisodesUseCase
import com.onats.rickandmorty.featurescomponents.utils.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class EpisodesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var getAllEpisodesUseCase: GetAllEpisodesUseCase

    private lateinit var viewModel: EpisodesViewModel

    @Before
    fun setup() {
        getAllEpisodesUseCase = GetAllEpisodesUseCase(FakeEpisodeRepositoryImpl())
    }

    @Test
    fun `test that viewModel can get all episodes`() = runTest {
        viewModel = EpisodesViewModel(getAllEpisodesUseCase)

        val result = viewModel.episodes.asSnapshot()
        advanceUntilIdle()
        assertThat(result).isNotEmpty()
    }
}