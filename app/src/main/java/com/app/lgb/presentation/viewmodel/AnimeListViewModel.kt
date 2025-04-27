package com.app.lgb.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.lgb.domain.model.AnimeItem
import com.app.lgb.domain.usecase.GetAnimeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val getAnimeListUseCase: GetAnimeListUseCase
) : ViewModel() {

    val _animeList = MutableStateFlow<List<AnimeItem>>(emptyList())
    var animeList: StateFlow<List<AnimeItem>> = _animeList

    init {
        fetchAnimeList()
    }

    private fun fetchAnimeList() {
        viewModelScope.launch {
            getAnimeListUseCase().collect {
                _animeList.value = it
            }
        }
    }
}
