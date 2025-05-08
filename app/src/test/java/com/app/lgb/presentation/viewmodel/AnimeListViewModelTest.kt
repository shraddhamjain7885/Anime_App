package com.app.lgb.presentation.viewmodel

import com.app.core.commondata.Resource
import com.app.domain.domain.model.AnimeItem
import com.app.domain.domain.usecase.GetAnimeListUseCase
import com.app.domain.domain.utils.Constant.NETWORK_ERROR
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.Assert.assertTrue
import org.junit.Test

@ExperimentalCoroutinesApi
class AnimeListViewModelTest {

    private lateinit var viewModel: AnimeListViewModel
    private lateinit var getAnimeListUseCase: GetAnimeListUseCase

    // The test dispatcher for coroutine execution
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        // Set the Main dispatcher for coroutines
        Dispatchers.setMain(testDispatcher)

        getAnimeListUseCase = mockk(relaxed = true) //  relaxed mock prevents "no answer found"
        viewModel = AnimeListViewModel(getAnimeListUseCase) //  same mock passed

    }

    @After
    fun tearDown() {
        // Reset the dispatcher after the tests finish
        Dispatchers.resetMain()
    }

    @Test
    fun `when fetchAnimeList is successful, then animeList should be updated`() = runTest {
        // Arrange
        val expectedList = listOf(
            AnimeItem(1, "AOT", "https://image.url/aot.jpg", "Titans attack."),
            AnimeItem(2, "Death Note", "https://image.url/dn.jpg", "Notebook kills.")
        )

        // Mock the use case to return Resource.Success
        coEvery { getAnimeListUseCase.invoke() } returns flowOf(Resource.Success(expectedList))

        // Act
        viewModel.fetchAnimeList()
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        val actual = viewModel.animeList.value
        assertTrue(actual is Resource.Success)
        assertEquals(expectedList, (actual as Resource.Success).data)
    }

    @Test
    fun `when fetchAnimeList returns error, then animeList should contain error message`() =
        runTest {
            coEvery { getAnimeListUseCase.invoke() } returns flowOf(Resource.Error(NETWORK_ERROR))

            viewModel.fetchAnimeList()
            testDispatcher.scheduler.advanceUntilIdle()

            val result = viewModel.animeList.value
            assertTrue(result is Resource.Error)
            assertEquals(NETWORK_ERROR, (result as Resource.Error).message)
        }

    @Test
    fun `when fetchAnimeList is loading, then animeList should be loading`() = runTest {
        coEvery { getAnimeListUseCase.invoke() } returns flow {
            emit(Resource.Loading())
            delay(1000) // simulate API delay
            emit(Resource.Success(emptyList()))
        }

        viewModel.fetchAnimeList()
        testDispatcher.scheduler.advanceTimeBy(100)

        val result = viewModel.animeList.value
        assertTrue(result is Resource.Loading)
    }

}