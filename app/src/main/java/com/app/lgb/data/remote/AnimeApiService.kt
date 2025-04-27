package com.app.lgb.data.remote

import retrofit2.http.GET


interface AnimeApiService {
    @GET("seasons/2013/summer?sfw")
    suspend fun getAnimeList(): AnimeResponseDto
}