package com.app.domain.usecase

import com.app.common.Resource
import com.app.domain.model.AnimeItem
import com.app.domain.repository.AnimeRepository
import com.app.domain.utils.Constant.NETWORK_ERROR
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert
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

        Assert.assertTrue(result.first() is Resource.Success)
        assertEquals(expected, (result.first() as Resource.Success).data)
    }

    @Test
    fun `invoke should return Resource Error`() = runTest {
        coEvery { repository.getAnimeList() } returns flowOf(Resource.Error(NETWORK_ERROR))

        val result = getAnimeListUseCase().toList()

        Assert.assertTrue(result.first() is Resource.Error)
        assertEquals(NETWORK_ERROR, (result.first() as Resource.Error).message)
    }

    @Test
    fun `invoke should return Resource Loading`() = runTest {
        coEvery { repository.getAnimeList() } returns flowOf(Resource.Loading())

        val result = getAnimeListUseCase().toList()

        Assert.assertTrue(result.first() is Resource.Loading)
    }
}