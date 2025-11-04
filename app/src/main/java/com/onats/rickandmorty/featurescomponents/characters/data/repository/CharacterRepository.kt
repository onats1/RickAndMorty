package com.onats.rickandmorty.featurescomponents.characters.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.onats.rickandmorty.featurescomponents.characters.data.paging.CharacterPagingSource
import com.onats.rickandmorty.featurescomponents.characters.data.remote.models.CharacterDto
import com.onats.rickandmorty.featurescomponents.characters.data.remote.models.toCharacter
import com.onats.rickandmorty.featurescomponents.characters.domain.models.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterRepository(
    private val characterPagingSource: CharacterPagingSource
) {

    fun getAllCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { characterPagingSource }
        ).flow.map { value: PagingData<CharacterDto> ->
            value.map { dto -> dto.toCharacter() }
        }
    }
}