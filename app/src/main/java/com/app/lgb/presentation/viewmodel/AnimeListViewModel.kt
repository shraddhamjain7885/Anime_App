package com.app.lgb.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.common.Resource
import com.app.domain.model.AnimeItem
import com.app.domain.usecase.GetAnimeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val getAnimeListUseCase: GetAnimeListUseCase
) : ViewModel() {

    private val _animeList = MutableStateFlow<Resource<List<AnimeItem>>>(Resource.Loading())
    val animeList: StateFlow<Resource<List<AnimeItem>>> = _animeList

    private val _selectedAnime = MutableStateFlow<AnimeItem?>(null)
    val selectedAnime: StateFlow<AnimeItem?> = _selectedAnime

    init {
        fetchAnimeList()
    }

    fun fetchAnimeList() {
        viewModelScope.launch {
            getAnimeListUseCase().collect { resource ->
                _animeList.value = resource
            }
        }
    }

    fun setSelectedAnime(anime: AnimeItem) {
        _selectedAnime.value = anime
    }

}
