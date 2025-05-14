package com.app.data.remote

import retrofit2.http.GET
import com.app.common.Constant.LIST_ENDPOINT

interface AnimeApiService {
    @GET(LIST_ENDPOINT)
    suspend fun getAnimeList(): AnimeResponseDto
}