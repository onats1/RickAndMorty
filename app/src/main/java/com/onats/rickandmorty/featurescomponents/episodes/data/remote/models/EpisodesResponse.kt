package com.onats.rickandmorty.featurescomponents.episodes.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodesResponse(
    @SerialName("info")
    val info: Info? = null,
    @SerialName("results")
    val results: List<EpisodeDto>? = null
)