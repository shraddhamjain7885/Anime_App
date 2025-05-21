package com.app.data.di

import com.app.common.Constant.BASE_URL
import com.app.data.remote.AnimeApiService
import com.app.domain.repository.AnimeRepository
import com.app.domain.usecase.GetAnimeListUseCase
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

    @Provides
    @Singleton
    fun provideAnimeApi(): AnimeApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimeApiService::class.java)
    }


    @Provides
    fun provideGetAnimeListUseCase(repository: AnimeRepository): GetAnimeListUseCase {
        return GetAnimeListUseCase(repository)
    }

}
