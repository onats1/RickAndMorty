package com.onats.rickandmorty.featurespresentation.episodes.models

import com.onats.rickandmorty.featurescomponents.episodes.data.remote.models.EpisodeDto
import com.onats.rickandmorty.featurescomponents.episodes.data.remote.models.EpisodesResponse
import com.onats.rickandmorty.featurescomponents.episodes.data.remote.models.Info

val fakeEpisodesDto = listOf(
    EpisodeDto(
        airDate = "December 2, 2013",
        characters = listOf(
            "https://rickandmortyapi.com/api/character/1",
            "https://rickandmortyapi.com/api/character/2"
        ),
        created = "2017-11-10T12:56:33.798Z",
        episode = "S01E01",
        id = 1,
        name = "Pilot",
        url = "https://rickandmortyapi.com/api/episode/1"
    ),
    EpisodeDto(
        airDate = "December 9, 2013",
        characters = listOf(
            "https://rickandmortyapi.com/api/character/1",
            "https://rickandmortyapi.com/api/character/3",
            "https://rickandmortyapi.com/api/character/7"
        ),
        created = "2017-11-10T12:56:33.916Z",
        episode = "S01E02",
        id = 2,
        name = "Lawnmower Dog",
        url = "https://rickandmortyapi.com/api/episode/2"
    ),
    EpisodeDto(
        airDate = "December 16, 2013",
        characters = listOf(
            "https://rickandmortyapi.com/api/character/2",
            "https://rickandmortyapi.com/api/character/4"
        ),
        created = "2017-11-10T12:56:34.022Z",
        episode = "S01E03",
        id = 3,
        name = "Anatomy Park",
        url = "https://rickandmortyapi.com/api/episode/3"
    ),
    EpisodeDto(
        airDate = "January 13, 2014",
        characters = listOf(
            "https://rickandmortyapi.com/api/character/1",
            "https://rickandmortyapi.com/api/character/5"
        ),
        created = "2017-11-10T12:56:34.129Z",
        episode = "S01E04",
        id = 4,
        name = "M. Night Shaym-Aliens!",
        url = "https://rickandmortyapi.com/api/episode/4"
    ),
    EpisodeDto(
        airDate = "January 20, 2014",
        characters = listOf(
            "https://rickandmortyapi.com/api/character/6",
            "https://rickandmortyapi.com/api/character/8"
        ),
        created = "2017-11-10T12:56:34.236Z",
        episode = "S01E05",
        id = 5,
        name = "Meeseeks and Destroy",
        url = "https://rickandmortyapi.com/api/episode/5"
    )
)


val fakeEpisodesResponse = EpisodesResponse(
    info = Info(
        count = 1,
        pages = 1,
        next = null,
        prev = null
    ),
    results = fakeEpisodesDto
)
