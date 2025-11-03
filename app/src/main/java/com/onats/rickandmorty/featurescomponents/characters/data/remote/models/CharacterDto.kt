package com.onats.rickandmorty.featurescomponents.characters.data.remote.models

import com.onats.rickandmorty.featurescomponents.characters.domain.models.Character
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    @SerialName("gender")
    val gender: String? = null,

    @SerialName("id")
    val id: Int? = null,

    @SerialName("image")
    val image: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("species")
    val species: String? = null,

    @SerialName("status")
    val status: String? = null,

    @SerialName("type")
    val type: String? = null,

    @SerialName("url")
    val url: String? = null
)

fun CharacterDto.toCharacter() = Character(
    gender = gender.orEmpty(),
    id = id ?: 0,
    image = image.orEmpty(),
    name = name.orEmpty(),
    species = species.orEmpty(),
    status = status.orEmpty(),
    type = type.orEmpty(),
    url = url.orEmpty()
)