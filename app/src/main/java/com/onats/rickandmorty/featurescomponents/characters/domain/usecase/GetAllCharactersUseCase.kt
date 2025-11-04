package com.onats.rickandmorty.featurescomponents.characters.domain.usecase

import com.onats.rickandmorty.featurescomponents.characters.domain.CharacterRepository

class GetAllCharactersUseCase(
    private val characterRepository: CharacterRepository
) {
    operator fun invoke() = characterRepository.getAllCharacters()
}