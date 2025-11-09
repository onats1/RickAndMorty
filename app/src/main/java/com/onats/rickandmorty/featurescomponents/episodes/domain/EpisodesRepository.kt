package com.onats.rickandmorty.featurescomponents.episodes.domain

import androidx.paging.PagingData
import com.onats.rickandmorty.featurescomponents.episodes.domain.models.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {
    fun getAllEpisodes(): Flow<PagingData<Episode>>
}