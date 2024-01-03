package com.reymon.firstapp.data.network.endpoints

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeEndPoint {
    @GET("anime/{id}/full") //Petición del endpoint(Endpoint)
    fun getAnimeFullInfo(@Path("id") name: Int):Response<com.reymon.firstapp.data.network.entities.anime.FullInfoAnime>


}
