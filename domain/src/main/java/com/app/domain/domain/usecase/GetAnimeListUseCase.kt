package com.app.domain.domain.usecase

import com.app.common.Resource
import com.app.domain.domain.model.AnimeItem
import com.app.domain.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnimeListUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(): Flow<Resource<List<AnimeItem>>> = repository.getAnimeList()
}

