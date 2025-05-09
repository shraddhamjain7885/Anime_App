package com.app.data.repository

import com.app.core.commondata.Resource
import com.app.data.mapper.toDomain
import com.app.data.remote.AnimeApiService
import com.app.domain.domain.model.AnimeItem
import com.app.domain.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val apiService: AnimeApiService
) : AnimeRepository {
    override fun getAnimeList(): Flow<Resource<List<AnimeItem>>> = flow {
        try {
            emit(Resource.Loading()) // Emit loading state
            val response = apiService.getAnimeList()
            val items = response.data.map { it.toDomain() }

            emit(Resource.Success(items)) // Emit success state
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString())) // Emit error state
        }
    }
}
