package com.onats.rickandmorty.featurescomponents.characters.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
    @SerialName("info")
    val info: Info? = null,
    @SerialName("results")
    val results: List<CharacterDto>? = null
)