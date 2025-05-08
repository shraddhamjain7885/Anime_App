package com.app.lgb.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.core.commondata.Resource
import com.app.domain.domain.model.AnimeItem
import com.app.domain.domain.usecase.GetAnimeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val getAnimeListUseCase: GetAnimeListUseCase
) : ViewModel() {

    val _animeList = MutableStateFlow<Resource<List<AnimeItem>>>(Resource.Loading())
    var animeList: StateFlow<Resource<List<AnimeItem>>> = _animeList

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
}
