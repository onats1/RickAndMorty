package com.onats.rickandmorty.featurespresentation.episodes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.onats.rickandmorty.app.utils.TestTags
import com.onats.rickandmorty.featurescomponents.episodes.domain.models.Episode
import com.onats.rickandmorty.featurespresentation.episodes.components.EpisodeTile
import com.onats.rickandmorty.featurespresentation.episodes.viewmodel.EpisodesViewModel
import kotlinx.coroutines.flow.flowOf

@Composable
fun EpisodesScreen(
    modifier: Modifier = Modifier,
    viewModel: EpisodesViewModel = hiltViewModel()
) {
    val episodesPagingItem = viewModel.episodes.collectAsLazyPagingItems()
    EpisodesScreen(episodes = episodesPagingItem, modifier = modifier)
}


@Composable
fun EpisodesScreen(
    modifier: Modifier = Modifier,
    episodes: LazyPagingItems<Episode>
) {
    Scaffold(modifier = modifier) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .testTag(TestTags.EPISODES_SCREEN_LAZY_COLUMN),
            contentPadding = PaddingValues(vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(episodes.itemCount) { index ->
                val episode = episodes[index] ?: Episode.model
                EpisodeTile(
                    episode = episode,
                    modifier = Modifier.testTag("EPISODE_ITEM${episode.id}")
                ) {}
            }
        }
    }
}

@Preview
@Composable
fun EpisodesScreenPreview() {
    val fakeEpisodes = listOf(
        Episode(
            id = 1,
            airDate = "December 2, 2013",
            characters = listOf("Rick Sanchez", "Morty Smith", "Beth Smith"),
            created = "2013-11-10T12:56:33.798Z",
            episode = "S01E01",
            name = "Pilot",
            url = "https://rickandmortyapi.com/api/episode/1"
        ),
        Episode(
            id = 2,
            airDate = "December 9, 2013",
            characters = listOf("Summer Smith", "Jerry Smith", "Mr. Meeseeks"),
            created = "2013-11-10T13:06:38.182Z",
            episode = "S01E02",
            name = "Lawnmower Dog",
            url = "https://rickandmortyapi.com/api/episode/2"
        ),
        Episode(
            id = 3,
            airDate = "December 16, 2013",
            characters = listOf("Rick Sanchez", "Morty Smith", "Snuffles"),
            created = "2013-11-10T13:08:43.864Z",
            episode = "S01E03",
            name = "Anatomy Park",
            url = "https://rickandmortyapi.com/api/episode/3"
        ),
        Episode(
            id = 4,
            airDate = "January 13, 2014",
            characters = listOf("Rick Sanchez", "Morty Smith", "Beth Smith", "Jerry Smith"),
            created = "2013-11-10T13:09:09.757Z",
            episode = "S01E04",
            name = "M. Night Shaym-Aliens!",
            url = "https://rickandmortyapi.com/api/episode/4"
        ),
        Episode(
            id = 5,
            airDate = "January 20, 2014",
            characters = listOf("Rick Sanchez", "Morty Smith", "Birdperson"),
            created = "2013-11-10T13:09:50.847Z",
            episode = "S01E05",
            name = "Meeseeks and Destroy",
            url = "https://rickandmortyapi.com/api/episode/5"
        )
    )

    val pagingItems = flowOf(PagingData.from(fakeEpisodes))
        .collectAsLazyPagingItems()
    EpisodesScreen(episodes = pagingItems)
}

