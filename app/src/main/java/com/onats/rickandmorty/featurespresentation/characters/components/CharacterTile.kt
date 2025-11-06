package com.onats.rickandmorty.featurespresentation.characters.components

import android.R.attr.contentDescription
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.onats.rickandmorty.featurescomponents.characters.domain.models.Character
import com.onats.rickandmorty.featurespresentation.characters.CharactersTestTags

@Composable
fun CharacterTile(
    modifier: Modifier = Modifier,
    character: Character,
    onClick: () -> Unit
) {
    Card (
        modifier = modifier
            .fillMaxWidth()
            .height(height = 150.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(12.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .background(color = Color.Transparent),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(character.image)
                    .crossfade(true)
                    .build(),
                contentDescription = contentDescription.toString(),
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(100.dp).width(100.dp)
                    .testTag(CharactersTestTags.CHARACTER_TILE_IMAGE)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.testTag(CharactersTestTags.CHARACTER_TILE_NAME)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = character.species,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.testTag(CharactersTestTags.CHARACTER_TILE_SPECIES)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = character.status,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.testTag(CharactersTestTags.CHARACTER_TILE_STATUS)
                )
            }
        }
    }
}

@Composable
@Preview
fun previewCharacterTile() {
    val character = Character(
        name = "Rick Sanchez",
        species = "Human",
        status = "Alive",
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        id = 1,
        gender = "Male",
        url = "https://rickandmortyapi.com/api/character/1",
        type = ""
    )
    CharacterTile(character = character) { }
}