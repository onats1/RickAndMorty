package com.onats.rickandmorty.featurespresentation.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.onats.rickandmorty.di.CharactersModule
import com.onats.rickandmorty.featurescomponents.characters.data.paging.CharacterPagingSource
import com.onats.rickandmorty.featurescomponents.characters.data.remote.CharactersApi
import com.onats.rickandmorty.featurescomponents.characters.data.repository.CharacterRepositoryImpl
import com.onats.rickandmorty.featurescomponents.characters.domain.CharacterRepository
import com.onats.rickandmorty.featurescomponents.characters.domain.usecase.GetAllCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CharactersModule::class]
)
object TestCharactersModule {

    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideCharactersApi(): CharactersApi {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl("http://localhost:8080")
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(CharactersApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCharacterPagingSource(charactersApi: CharactersApi): CharacterPagingSource {
        return CharacterPagingSource(charactersApi)
    }

    @Provides
    @Singleton
    fun provideCharactersRepository(
        characterPagingSource: CharacterPagingSource
    ): CharacterRepository {
        return CharacterRepositoryImpl(characterPagingSource = characterPagingSource)
    }

    @Provides
    @Singleton
    fun provideGetCharactersUseCase(characterRepository: CharacterRepository): GetAllCharactersUseCase {
        return GetAllCharactersUseCase(characterRepository)
    }
}