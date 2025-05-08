package com.app.data.remote

import com.app.core.commondata.Constant
import retrofit2.http.GET

interface AnimeApiService {
    @GET(Constant.LIST_ENDPOINT)
    suspend fun getAnimeList(): AnimeResponseDto
}