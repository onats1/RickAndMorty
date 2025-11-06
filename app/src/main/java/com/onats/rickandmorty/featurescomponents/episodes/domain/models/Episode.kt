package com.onats.rickandmorty.featurescomponents.episodes.domain.models

data class Episode(
    val id: Int,
    val airDate: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val name: String,
    val url: String
)
