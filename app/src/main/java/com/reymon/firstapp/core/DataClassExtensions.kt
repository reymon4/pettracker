package com.reymon.firstapp.core

import com.reymon.firstapp.data.network.entities.anime.FullInfoAnime
import com.reymon.firstapp.logic.usercases.jikan.entities.FullInfoAnimeLG

fun FullInfoAnime.getFullInfoAnimeLG() = FullInfoAnimeLG(
    this.data.mal_id,
    this.data.title_english,
    this.data.images.jpg.small_image_url,
    this.data.images.jpg.large_image_url,
    this.data.synopsis

)