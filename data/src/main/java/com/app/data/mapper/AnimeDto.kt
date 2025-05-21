package com.app.data.mapper

import com.app.data.remote.AnimeDto
import com.app.domain.model.AnimeItem

fun AnimeDto.toDomain(): AnimeItem {
    return AnimeItem(
        malId = this.mal_id,
        title = this.title,
        imageUrl = this.images.jpg.image_url,
        synopsis = this.synopsis
    )
}