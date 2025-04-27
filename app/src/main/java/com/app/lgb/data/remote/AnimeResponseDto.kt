package com.app.lgb.data.remote

data class AnimeResponseDto(
    val data: List<AnimeDto>
)

data class AnimeDto(
    val mal_id: Int,
    val title: String,
    val images: ImagesDto,
    val synopsis: String
)

data class ImagesDto(
    val jpg: JpgDto
)

data class JpgDto(
    val image_url: String
)