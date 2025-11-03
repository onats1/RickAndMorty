package com.onats.rickandmorty.featurescomponents.characters.data.fakes

import com.onats.rickandmorty.featurescomponents.characters.data.remote.models.CharacterDto
import com.onats.rickandmorty.featurescomponents.characters.data.remote.models.CharactersResponse
import com.onats.rickandmorty.featurescomponents.characters.data.remote.models.Info

val fakeCharactersDto: List<CharacterDto> = listOf(
    CharacterDto(
        gender = "Male",
        id = 1,
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        name = "Rick Sanchez",
        species = "Human",
        type = "",
        url = "",
        status = "Alive"
    ),
    CharacterDto(
        gender = "Female",
        id = 2,
        image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
        name = "Morty Smith",
        species = "Human",
        type = "",
        url = "",
        status = "Alive"
    ),
    CharacterDto(
        gender = "Male",
        id = 3,
        image = "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
        name = "Summer Smith",
        species = "Human",
        type = "",
        url = "",
        status = "Alive"
    ),
    CharacterDto(
        gender = "Male",
        id = 4,
        image = "https://rickandmortyapi.com/api/character/avatar/4.jpeg",
        name = "Beth Smith",
        species = "Human",
        type = "",
        url = "",
        status = "Alive"
    ),
    CharacterDto(
        gender = "Male",
        id = 5,
        image = "https://rickandmortyapi.com/api/character/avatar/5.jpeg",
        name = "Jerry Smith",
        species = "Human",
        type = "",
        url = "",
        status = "Alive"
    ),
    CharacterDto(
        gender = "Male",
        id = 6,
        image = "https://rickandmortyapi.com/api/character/avatar/6.jpeg",
        name = "Abadango Cluster Princess",
        species = "Alien",
        type = "",
        url = "",
        status = "Alive"
    ),
    CharacterDto(
        gender = "Female",
        id = 7,
        image = "https://rickandmortyapi.com/api/character/avatar/7.jpeg",
        name = "Abradolf Lincler",
        species = "Human",
        type = "Genetic experiment",
        url = "",
        status = "unknown"
    ),
    CharacterDto(
        gender = "Male",
        id = 8,
        image = "https://rickandmortyapi.com/api/character/avatar/8.jpeg",
        name = "Adjudicator Rick",
        species = "Human",
        type = "",
        url = "",
        status = "Dead"
    ),
    CharacterDto(
        gender = "Male",
        id = 9,
        image = "https://rickandmortyapi.com/api/character/avatar/9.jpeg",
        name = "Agency Director",
        species = "Human",
        type = "",
        url = "",
        status = "Dead"
    ),
)

val fakeCharacterResponse = CharactersResponse(
    info = Info(
        count = 1,
        pages = 1,
        next = null,
        prev = null
    ),
    results = fakeCharactersDto
)
