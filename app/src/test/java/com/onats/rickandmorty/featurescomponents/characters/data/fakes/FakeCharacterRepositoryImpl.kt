package com.onats.rickandmorty.featurescomponents.characters.data.fakes

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.testing.asPagingSourceFactory
import com.onats.rickandmorty.featurescomponents.characters.data.remote.models.toCharacter
import com.onats.rickandmorty.featurescomponents.characters.domain.CharacterRepository
import com.onats.rickandmorty.featurescomponents.characters.domain.models.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FakeCharacterRepositoryImpl: CharacterRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = fakeCharactersDto.asPagingSourceFactory()
        ).flow.map { pagingData ->
            pagingData.map { it.toCharacter() }
        }
    }
}