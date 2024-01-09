package com.reymon.firstapp.data.network.endpoints


import com.reymon.firstapp.data.network.entities.top.TopAnimesDataClass
import retrofit2.Response
import retrofit2.http.GET

interface TopAnimesEndPoint {
    @GET("top/anime")
    suspend fun getAllTopAnimes() : Response<TopAnimesDataClass>
}