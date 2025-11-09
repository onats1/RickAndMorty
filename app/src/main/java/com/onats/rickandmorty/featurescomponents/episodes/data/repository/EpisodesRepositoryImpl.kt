package com.onats.rickandmorty.featurescomponents.episodes.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.onats.rickandmorty.featurescomponents.episodes.data.paging.EpisodePagingSource
import com.onats.rickandmorty.featurescomponents.episodes.data.remote.models.EpisodeDto
import com.onats.rickandmorty.featurescomponents.episodes.data.remote.models.toEpisode
import com.onats.rickandmorty.featurescomponents.episodes.domain.EpisodesRepository
import com.onats.rickandmorty.featurescomponents.episodes.domain.models.Episode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EpisodesRepositoryImpl(
    private val episodesPagingSource: EpisodePagingSource
): EpisodesRepository {
    override fun getAllEpisodes(): Flow<PagingData<Episode>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { episodesPagingSource }
        ).flow.map { value: PagingData<EpisodeDto> ->
            value.map { dto -> dto.toEpisode() }
        }
    }
}