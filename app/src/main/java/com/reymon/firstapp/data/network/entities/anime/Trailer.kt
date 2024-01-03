package com.reymon.firstapp.data.network.entities.anime

data class Trailer(
    val embed_url: String,
    val images: com.reymon.firstapp.data.network.entities.anime.ImagesX,
    val url: String,
    val youtube_id: String
)