package com.reymon.firstapp.data.network.endpoints

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBase { //Creo un método para cada api que necesite
    private const val JikanUrl = "https://api.jikan.moe/v4/"
    fun getRetrofitJikanConnection(): Retrofit {
        //Creamos un objeto "Builder" y lo convierte para realizar la conexión
        return Retrofit.Builder().baseUrl(JikanUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

}