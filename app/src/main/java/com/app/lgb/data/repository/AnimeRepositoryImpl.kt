package com.app.lgb.data.repository



import com.app.lgb.data.remote.AnimeApiService
import com.app.lgb.domain.model.AnimeItem
import com.app.lgb.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val apiService: AnimeApiService
) : AnimeRepository {
    override fun getAnimeList(): Flow<List<AnimeItem>> = flow {
        val response = apiService.getAnimeList()
        val items = response.data.map {
            AnimeItem(
                malId = it.mal_id,
                title = it.title,
                imageUrl = it.images.jpg.image_url,
                synopsis = it.synopsis
            )
        }
        emit(items)
    }
}
