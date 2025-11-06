package com.onats.rickandmorty.featurespresentation.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.onats.rickandmorty.app.utils.TestTags
import com.onats.rickandmorty.featurescomponents.characters.domain.models.Character
import com.onats.rickandmorty.featurespresentation.characters.components.CharacterTile
import com.onats.rickandmorty.featurespresentation.characters.viewmodel.CharactersViewModel
import kotlinx.coroutines.flow.flowOf

@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    viewModel: CharactersViewModel = hiltViewModel()
) {
    val charactersPagingItems = viewModel.characters.collectAsLazyPagingItems()
    CharactersScreen(characters = charactersPagingItems, modifier = modifier)
}

@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    characters: LazyPagingItems<Character>
) {
    Scaffold(modifier = modifier) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
                .testTag(TestTags.CHARACTERS_SCREEN_LAZY_COLUMN),
            contentPadding = PaddingValues(vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(characters.itemCount) { index ->
                val character = characters[index] ?: Character.model
                CharacterTile(character = character, modifier = Modifier.testTag("CHARACTER_ITEM_${character.id}")) {

                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewCharactersScreen() {
    val sampleCharacters = listOf(
        Character(
            id = 1,
            name = "Rick Sanchez",
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            gender = "Male",
            species = "Human",
            status = "Alive",
            type = "",
            url = "https://rickandmortyapi.com/api/character/1"
        ),
        Character(
            id = 2,
            name = "Morty Smith",
            image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
            gender = "Male",
            species = "Human",
            status = "Alive",
            type = "",
            url = "https://rickandmortyapi.com/api/character/2"
        ),
        Character(
            id = 3,
            name = "Summer Smith",
            image = "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
            gender = "Female",
            species = "Human",
            status = "Alive",
            type = "",
            url = "https://rickandmortyapi.com/api/character/3"
        ),
        Character(
            id = 4,
            name = "Beth Smith",
            image = "https://rickandmortyapi.com/api/character/avatar/4.jpeg",
            gender = "Female",
            species = "Human",
            status = "Alive",
            type = "",
            url = "https://rickandmortyapi.com/api/character/4"
        ),
        Character(
            id = 5,
            name = "Jerry Smith",
            image = "https://rickandmortyapi.com/api/character/avatar/5.jpeg",
            gender = "Male",
            species = "Human",
            status = "Alive",
            type = "",
            url = "https://rickandmortyapi.com/api/character/5"
        )
    )


    val pagingItems = flowOf(PagingData.from(sampleCharacters))
        .collectAsLazyPagingItems()
    CharactersScreen(
        characters = pagingItems
    )
}
