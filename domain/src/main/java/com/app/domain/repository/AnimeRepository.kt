package com.app.domain.repository

import com.app.domain.model.AnimeItem
import com.app.common.Resource
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    fun getAnimeList(): Flow<Resource<List<AnimeItem>>>
}