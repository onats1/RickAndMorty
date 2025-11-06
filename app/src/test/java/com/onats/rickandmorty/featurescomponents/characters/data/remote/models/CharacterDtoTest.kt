package com.onats.rickandmorty.featurescomponents.characters.data.remote.models

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CharacterDtoTest {

    private val testModel = CharacterDto(
        gender = "Male",
        id = 1,
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        name = "Rick Sanchez",
        species = "Human",
        status = null,
        type = "human",
        url = null
    )

    @Test
    fun `toCharacterModel returns correct character model`() {
        val character = testModel.toCharacter()
        assertThat(character.url).isEmpty()
        assertThat(character.name).isEqualTo(testModel.name)
        assertThat(character.gender).isEqualTo(testModel.gender)
        assertThat(character.id).isEqualTo(testModel.id)
        assertThat(character.status).isEmpty()
    }
}