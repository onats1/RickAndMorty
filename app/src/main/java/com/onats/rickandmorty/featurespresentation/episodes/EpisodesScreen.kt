package com.onats.rickandmorty.featurespresentation.episodes

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun EpisodesScreen(modifier: Modifier = Modifier) {
    Scaffold { innerPadding ->
        Text(text = "Episodes", modifier = modifier.padding(innerPadding))
    }
}