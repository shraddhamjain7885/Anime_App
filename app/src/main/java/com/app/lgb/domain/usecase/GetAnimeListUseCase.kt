package com.app.lgb.domain.usecase


import com.app.lgb.domain.model.AnimeItem
import com.app.lgb.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnimeListUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(): Flow<List<AnimeItem>> = repository.getAnimeList()
}

