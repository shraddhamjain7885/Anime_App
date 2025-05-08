package com.app.domain.domain.repository

import com.app.core.commondata.Resource
import com.app.domain.domain.model.AnimeItem
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    fun getAnimeList(): Flow<Resource<List<AnimeItem>>>
}