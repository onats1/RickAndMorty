package com.onats.rickandmorty.featurespresentation.characters.viewmodel

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import com.google.common.truth.Truth.assertThat
import com.onats.rickandmorty.featurescomponents.characters.data.fakes.FakeCharacterRepositoryImpl
import com.onats.rickandmorty.featurescomponents.characters.data.fakes.fakeCharactersDto
import com.onats.rickandmorty.featurescomponents.characters.data.remote.models.toCharacter
import com.onats.rickandmorty.featurescomponents.characters.domain.usecase.GetAllCharactersUseCase
import com.onats.rickandmorty.featurescomponents.utils.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class CharactersViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var getAllCharactersUseCase: GetAllCharactersUseCase

    private lateinit var viewModel: CharactersViewModel

    @Before
    fun setup() {
        getAllCharactersUseCase = GetAllCharactersUseCase(FakeCharacterRepositoryImpl())
    }

    @Test
    fun `test that viewModel can get all characters`() = runTest {
        viewModel = CharactersViewModel(getAllCharactersUseCase)

        val result = viewModel.characters.asSnapshot()
        advanceUntilIdle()
        assertThat(result).isNotEmpty()
    }


}