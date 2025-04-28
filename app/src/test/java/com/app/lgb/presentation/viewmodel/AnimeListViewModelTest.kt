package com.app.lgb.presentation.viewmodel

import com.app.lgb.domain.model.AnimeItem
import com.app.lgb.domain.usecase.GetAnimeListUseCase
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
import kotlinx.coroutines.test.*
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
        // Arrange: Set up the expected list of anime items
        val expectedList = listOf(
            AnimeItem(1, "AOT", "https://image.url/aot.jpg", "Titans attack."),
            AnimeItem(2, "Death Note", "https://image.url/dn.jpg", "Notebook kills.")
        )

        // Mock the use case to return the expected list of anime items
        coEvery { getAnimeListUseCase.invoke() } returns flowOf(expectedList)

        viewModel.fetchAnimeList()

        // Act: Allow coroutines to finish their work
        testDispatcher.scheduler.advanceUntilIdle()
        // Assert: Check that the ViewModel's animeList StateFlow has the expected value
        assertEquals(expectedList, viewModel.animeList.value)
    }


    @Test
    fun `when fetchAnimeList returns empty, then animeList should be empty`() = runTest {
        // Arrange: Set up an empty list of anime items
        val expectedEmptyList = emptyList<AnimeItem>()

        // Mock the use case to return an empty list
        coEvery { getAnimeListUseCase() } returns flowOf(expectedEmptyList)

        // Act: Allow coroutines to finish their work
        testDispatcher.scheduler.advanceUntilIdle() // Ensures the ViewModel is done collecting data

        // Assert: Check that the ViewModel's animeList StateFlow is empty
        assertEquals(expectedEmptyList, viewModel.animeList.value)
    }
}