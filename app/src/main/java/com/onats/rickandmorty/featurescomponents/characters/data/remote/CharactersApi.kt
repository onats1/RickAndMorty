package com.onats.rickandmorty.featurescomponents.characters.data.remote

import com.onats.rickandmorty.featurescomponents.characters.data.remote.models.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET

interface CharactersApi {

    companion object {
        const val GET_ALL_CHARACTERS = "api/character"
    }

    @GET(GET_ALL_CHARACTERS)
    suspend fun getAllCharacters(): Response<CharactersResponse>

}
