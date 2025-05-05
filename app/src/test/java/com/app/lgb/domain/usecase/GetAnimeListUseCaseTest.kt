package com.app.lgb.domain.usecase

import com.app.core.commondata.Resource
import com.app.lgb.domain.model.AnimeItem
import com.app.lgb.domain.repository.AnimeRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals


@OptIn(ExperimentalCoroutinesApi::class)
class GetAnimeListUseCaseTest {

    private lateinit var repository: AnimeRepository
    private lateinit var getAnimeListUseCase: GetAnimeListUseCase

    @Before
    fun setUp() {
        repository = mockk()
        getAnimeListUseCase = GetAnimeListUseCase(repository)
    }

    @Test
    fun `invoke should return Resource Success`() = runTest {
        val expected = listOf(
            AnimeItem(1, "Naruto", "url1", "desc1")
        )
        coEvery { repository.getAnimeList() } returns flowOf(Resource.Success(expected))

        val result = getAnimeListUseCase().toList()

        assertTrue(result.first() is Resource.Success)
        assertEquals(expected, (result.first() as Resource.Success).data)
    }

    @Test
    fun `invoke should return Resource Error`() = runTest {
        val error = "Network error"
        coEvery { repository.getAnimeList() } returns flowOf(Resource.Error(error))

        val result = getAnimeListUseCase().toList()

        assertTrue(result.first() is Resource.Error)
        assertEquals(error, (result.first() as Resource.Error).message)
    }

    @Test
    fun `invoke should return Resource Loading`() = runTest {
        coEvery { repository.getAnimeList() } returns flowOf(Resource.Loading())

        val result = getAnimeListUseCase().toList()

        assertTrue(result.first() is Resource.Loading)
    }
}
