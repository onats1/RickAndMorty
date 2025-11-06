package com.onats.rickandmorty.featurescomponents.episodes.data.remote

import com.onats.rickandmorty.featurescomponents.episodes.data.remote.models.EpisodesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodesApi {

    companion object {
        const val GET_ALL_EPISODES = "api/episode"
    }

    @GET(GET_ALL_EPISODES)
    suspend fun getAllEpisodes(
        @Query("page") page: Int = 1
    ): Response<EpisodesResponse>
}

