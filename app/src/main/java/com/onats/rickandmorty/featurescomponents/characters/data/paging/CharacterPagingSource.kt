package com.onats.rickandmorty.featurescomponents.characters.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.onats.rickandmorty.featurescomponents.characters.data.remote.CharactersApi
import com.onats.rickandmorty.featurescomponents.characters.data.remote.models.CharacterDto

class CharacterPagingSource(
    private val charactersApi: CharactersApi
): PagingSource<Int, CharacterDto>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDto> {
        try {
            val nextPage = params.key ?: 1
            val charactersResponse = charactersApi.getAllCharacters(nextPage)

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