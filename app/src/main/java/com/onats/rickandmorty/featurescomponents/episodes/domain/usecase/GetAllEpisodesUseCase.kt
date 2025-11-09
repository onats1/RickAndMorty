package com.onats.rickandmorty.featurescomponents.episodes.domain.usecase

import com.onats.rickandmorty.featurescomponents.episodes.domain.EpisodesRepository

class GetAllEpisodesUseCase(
    private val episodesRepository: EpisodesRepository
) {
    operator fun invoke() = episodesRepository.getAllEpisodes()
}