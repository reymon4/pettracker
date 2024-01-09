package com.reymon.firstapp.data.network.entities.top

data class TopAnimesDataClass(
    val `data`: List<Data> = listOf(),
    val pagination: Pagination? =null
)