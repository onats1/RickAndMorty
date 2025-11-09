package com.onats.rickandmorty.featurespresentation.episodes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.onats.rickandmorty.featurescomponents.episodes.domain.usecase.GetAllEpisodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel
@Inject
constructor(
    private val getAllEpisodesUseCase: GetAllEpisodesUseCase
): ViewModel() {

    val episodes = getAllEpisodesUseCase.invoke()
        .cachedIn(viewModelScope)
}