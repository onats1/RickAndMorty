package com.onats.rickandmorty.featurespresentation.characters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.onats.rickandmorty.featurescomponents.characters.domain.usecase.GetAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel
@Inject
constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : ViewModel() {

    val characters = getAllCharactersUseCase.invoke()
        .cachedIn(viewModelScope)

}