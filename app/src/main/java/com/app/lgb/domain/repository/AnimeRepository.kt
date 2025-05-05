package com.app.lgb.domain.repository

import com.app.core.commondata.Resource
import com.app.lgb.domain.model.AnimeItem
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    fun getAnimeList(): Flow<Resource<List<AnimeItem>>>
}