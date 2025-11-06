package com.onats.rickandmorty.featurescomponents.episodes.data.remote.models

import com.onats.rickandmorty.featurescomponents.episodes.domain.models.Episode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeDto(
    @SerialName("air_date")
    val airDate: String? = null,

    @SerialName("characters")
    val characters: List<String>? = null,

    @SerialName("created")
    val created: String? = null,

    @SerialName("episode")
    val episode: String? = null,

    @SerialName("id")
    val id: Int? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("url")
    val url: String? = null
)


fun EpisodeDto.toEpisode(): Episode {
    return Episode(
        airDate = airDate.orEmpty(),
        characters = characters.orEmpty(),
        created = created.orEmpty(),
        episode = episode.orEmpty(),
        id = id?:0,
        name = name.orEmpty(),
        url = url.orEmpty()
    )
}