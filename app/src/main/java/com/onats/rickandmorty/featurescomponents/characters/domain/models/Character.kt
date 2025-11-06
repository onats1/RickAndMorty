package com.onats.rickandmorty.featurescomponents.characters.domain.models

data class Character(
    val gender: String,
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
) {
    companion object Default {
        val model = Character(
            gender = "",
            id = 0,
            image = "",
            name = "",
            species = "",
            status = "",
            type = "",
            url = ""
        )
    }
}