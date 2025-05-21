package com.app.domain.usecase

import com.app.common.Resource
import com.app.domain.model.AnimeItem
import com.app.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow

class GetAnimeListUseCase(private val repository: AnimeRepository) {
    operator fun invoke(): Flow<Resource<List<AnimeItem>>> {
        return repository.getAnimeList()
    }
}