package com.onats.rickandmorty.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.onats.rickandmorty.featurescomponents.characters.data.paging.CharacterPagingSource
import com.onats.rickandmorty.featurescomponents.characters.data.remote.CharactersApi
import com.onats.rickandmorty.featurescomponents.characters.data.repository.CharacterRepositoryImpl
import com.onats.rickandmorty.featurescomponents.characters.domain.CharacterRepository
import com.onats.rickandmorty.featurescomponents.characters.domain.usecase.GetAllCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharactersModule {

    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideCharactersApi(): CharactersApi {
        val contentType = "application/json".toMediaType()
        val logging = HttpLoggingInterceptor().apply {
            // Choose how much detail you want in logs
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(okHttpClient)
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
    fun provideCharactersUseCase(
        characterRepository: CharacterRepository
    ): GetAllCharactersUseCase {
        return GetAllCharactersUseCase(characterRepository)
    }

}