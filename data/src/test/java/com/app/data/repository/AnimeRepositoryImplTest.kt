package com.app.data.repository

import com.app.common.Resource
import com.app.data.remote.AnimeApiService
import com.app.data.remote.AnimeDto
import com.app.data.remote.AnimeResponseDto
import com.app.data.remote.ImagesDto
import com.app.data.remote.JpgDto
import com.app.domain.domain.utils.Constant.API_FAILURE
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class AnimeRepositoryImplTest {

    private lateinit var repository: AnimeRepositoryImpl
    private val apiService = mockk<AnimeApiService>()

    @Before
    fun setup() {
        repository = AnimeRepositoryImpl(apiService)
    }

    @Test
    fun `getAnimeList emits loading and success when api call is successful`() = runTest {
        // Arrange
        val apiResponse = AnimeResponseDto(
            data = listOf(
                AnimeDto(
                    mal_id = 1,
                    title = "Naruto",
                    images = ImagesDto(
                        jpg = JpgDto(image_url = "image_url")
                    ),
                    synopsis = "A ninja story"
                )
            )
        )
        coEvery { apiService.getAnimeList() } returns apiResponse

        // Act
        val emissions = repository.getAnimeList().toList()

        // Assert
        assert(emissions[0] is Resource.Loading)
        assert(emissions[1] is Resource.Success)
        val items = (emissions[1] as Resource.Success).data
        assertEquals("Naruto", items.orEmpty()[0].title)
        assertEquals("image_url", items.orEmpty()[0].imageUrl)
    }

    @Test
    fun `getAnimeList emits loading and error when api call throws exception`() = runTest {
        // Arrange
        coEvery { apiService.getAnimeList() } throws RuntimeException(API_FAILURE)

        // Act
        val emissions = repository.getAnimeList().toList()

        // Assert
        assert(emissions[0] is Resource.Loading)
        assert(emissions[1] is Resource.Error)
        val errorMessage = (emissions[1] as Resource.Error).message
        assertEquals(API_FAILURE, errorMessage)
    }
}
