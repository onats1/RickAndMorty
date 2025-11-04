package com.onats.rickandmorty.featurescomponents.characters.data.repository

import androidx.paging.testing.asSnapshot
import com.google.common.truth.Truth.assertThat
import com.onats.rickandmorty.featurescomponents.characters.data.fakes.fakeCharacterResponse
import com.onats.rickandmorty.featurescomponents.characters.data.fakes.fakeCharactersDto
import com.onats.rickandmorty.featurescomponents.characters.data.paging.CharacterPagingSource
import com.onats.rickandmorty.featurescomponents.characters.data.remote.CharactersApi
import com.onats.rickandmorty.featurescomponents.characters.data.remote.models.toCharacter
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
class CharacterRepositoryTest {

    @Mock
    private lateinit var characterApi: CharactersApi

    private lateinit var characterPagingSource: CharacterPagingSource

    @get:Rule
    val mainCoroutineRule = MainDispatcherRule()

    private lateinit var characterRepository: CharacterRepository

    @Before
    fun setup() {
        characterPagingSource = CharacterPagingSource(characterApi)
        characterRepository = CharacterRepository(characterPagingSource)
    }

    @Test
    fun `test getAllCharacters returns paginated data with mapped characters`() = runTest {

        whenever(characterApi.getAllCharacters(any())).thenReturn(Response.success(fakeCharacterResponse))

        val result = characterRepository.getAllCharacters()

        val pagingData = result.asSnapshot()

        val characters = fakeCharactersDto.map { it.toCharacter() }

        assertThat(pagingData).isNotEmpty()
        assertThat(pagingData.size).isEqualTo(characters.size)
        assertThat(pagingData).containsExactlyElementsIn(characters)
    }


}