package com.app.lgb.di

import com.app.lgb.data.remote.AnimeApiService
import com.app.lgb.data.repository.AnimeRepositoryImpl
import com.app.lgb.domain.repository.AnimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://api.jikan.moe/v4/"

    @Singleton
    @Provides
    fun provideAnimeApi(): AnimeApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimeApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAnimeRepository(api: AnimeApiService): AnimeRepository {
        return AnimeRepositoryImpl(api)
    }
}
