package com.onats.rickandmorty.featurescomponents.characters.domain.usecase

import androidx.paging.testing.asSnapshot
import com.google.common.truth.Truth.assertThat
import com.onats.rickandmorty.featurescomponents.characters.data.fakes.FakeCharacterRepositoryImpl
import com.onats.rickandmorty.featurescomponents.characters.domain.CharacterRepository
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class GetAllCharactersUseCaseTest {

    private lateinit var characterRepository: CharacterRepository
    private lateinit var getAllCharactersUseCase: GetAllCharactersUseCase

    @Before
    fun setup() {
        characterRepository = FakeCharacterRepositoryImpl()
        getAllCharactersUseCase = GetAllCharactersUseCase(characterRepository)
    }

    @Test
    fun `invoke returns flow of paginated characters`() = runTest {
        val result = getAllCharactersUseCase.invoke()
        val pagingData = result.asSnapshot()
        assertThat(pagingData).isNotEmpty()
        assertThat(pagingData.first().id).isEqualTo(1)
    }

}