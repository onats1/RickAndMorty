package com.onats.rickandmorty.featurescomponents.characters.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Info(
    @SerialName("count")
    val count: Int,

    @SerialName("next")
    val next: String? = null,

    @SerialName("pages")
    val pages: Int? = null,

    @SerialName("prev")
    val prev: String? = null
)