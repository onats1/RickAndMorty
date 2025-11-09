package com.onats.rickandmorty.featurespresentation.episodes.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onats.rickandmorty.featurescomponents.episodes.domain.models.Episode
import com.onats.rickandmorty.featurespresentation.characters.CharactersTestTags
import com.onats.rickandmorty.featurespresentation.episodes.EpisodesTestTags

@Composable
fun EpisodeTile(
    modifier: Modifier = Modifier,
    episode: Episode,
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
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        ) {
            Text(
                text = episode.name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.testTag(EpisodesTestTags.EPISODE_TILE_TITLE)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Aired: ${episode.airDate}",
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.testTag(EpisodesTestTags.EPISODE_AIR_DATE)
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Preview
@Composable
fun EpisodeTilePreview() {
    val episode = Episode(
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
    EpisodeTile(
        episode = episode,
        onClick = {  }
    )
}