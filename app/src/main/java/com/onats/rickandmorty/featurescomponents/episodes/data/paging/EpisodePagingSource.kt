package com.onats.rickandmorty.featurescomponents.episodes.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.onats.rickandmorty.featurescomponents.episodes.data.remote.EpisodesApi
import com.onats.rickandmorty.featurescomponents.episodes.data.remote.models.EpisodeDto

class EpisodePagingSource(
    private val episodesApi: EpisodesApi
): PagingSource<Int, EpisodeDto>() {
    override fun getRefreshKey(state: PagingState<Int, EpisodeDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeDto> {
        try {
            val nextPage = params.key ?: 1
            val charactersResponse = episodesApi.getAllEpisodes(nextPage)

            if (charactersResponse.isSuccessful) {
                val characters = charactersResponse.body()?.results ?: emptyList()
                return LoadResult.Page(
                    data = characters,
                    prevKey = null,
                    nextKey = if (charactersResponse.body()?.info?.next != null) nextPage + 1 else null
                )
            } else {
                return LoadResult.Error(Exception("Failed to fetch characters"))
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}