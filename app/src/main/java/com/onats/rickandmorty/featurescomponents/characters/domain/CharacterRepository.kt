package com.onats.rickandmorty.featurescomponents.characters.domain

import androidx.paging.PagingData
import com.onats.rickandmorty.featurescomponents.characters.domain.models.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getAllCharacters(): Flow<PagingData<Character>>
}


