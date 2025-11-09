package com.onats.rickandmorty.featurespresentation.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.onats.rickandmorty.di.EpisodesModule
import com.onats.rickandmorty.featurescomponents.episodes.data.paging.EpisodePagingSource
import com.onats.rickandmorty.featurescomponents.episodes.data.remote.EpisodesApi
import com.onats.rickandmorty.featurescomponents.episodes.data.repository.EpisodesRepositoryImpl
import com.onats.rickandmorty.featurescomponents.episodes.domain.EpisodesRepository
import com.onats.rickandmorty.featurescomponents.episodes.domain.usecase.GetAllEpisodesUseCase
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
    replaces = [EpisodesModule::class]
)
object TestEpisodesModule {

    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideEpisodesApi(): EpisodesApi {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl("http://localhost:8080")
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(EpisodesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideEpisodesPagingSource(episodesApi: EpisodesApi): EpisodePagingSource {
        return EpisodePagingSource(episodesApi)
    }

    @Provides
    @Singleton
    fun provideEpisodesRepository(episodePagingSource: EpisodePagingSource): EpisodesRepository {
        return EpisodesRepositoryImpl(episodesPagingSource = episodePagingSource)
    }

    @Provides
    @Singleton
    fun provideGetEpisodesUseCase(episodesRepository: EpisodesRepository): GetAllEpisodesUseCase {
        return GetAllEpisodesUseCase(episodesRepository)
    }
}