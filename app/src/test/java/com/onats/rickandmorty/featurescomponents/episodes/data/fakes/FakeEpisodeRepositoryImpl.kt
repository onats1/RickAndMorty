package com.onats.rickandmorty.featurescomponents.episodes.data.fakes

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.testing.asPagingSourceFactory
import com.onats.rickandmorty.featurescomponents.episodes.data.remote.models.toEpisode
import com.onats.rickandmorty.featurescomponents.episodes.domain.EpisodesRepository
import com.onats.rickandmorty.featurescomponents.episodes.domain.models.Episode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FakeEpisodeRepositoryImpl: EpisodesRepository {
    override fun getAllEpisodes(): Flow<PagingData<Episode>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = fakeEpisodesDto.asPagingSourceFactory()
        ).flow.map { pagingData ->
            pagingData.map { it.toEpisode() }
        }
    }
}