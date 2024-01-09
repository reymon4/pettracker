package com.reymon.firstapp.logic.usercases.jikan

import android.util.Log
import com.reymon.firstapp.core.Constants
import com.reymon.firstapp.core.getFullInfoAnimeLG
import com.reymon.firstapp.data.network.endpoints.AnimeEndPoint
import com.reymon.firstapp.data.network.endpoints.TopAnimesEndPoint

import com.reymon.firstapp.data.network.repository.RetrofitBase
import com.reymon.firstapp.logic.usercases.jikan.entities.FullInfoAnimeLG
import java.util.Collections

class JikanUserCase {
    fun getFullAnimeInfo(idAnime: Int): FullInfoAnimeLG {
        val baseService = RetrofitBase.getRetrofitJikanConnection()
        val service = baseService.create(AnimeEndPoint::class.java)
        val call = service.getAnimeFullInfo(idAnime)

        var infoAnime = FullInfoAnimeLG()
            if (call.isSuccessful) {
//            //PARA llamar a los datos debemos hacer con una corrutina
//            infoAnime.id = call.body()!!.data.mal_id
//            infoAnime.name = call.body()!!.data.title_english
//            infoAnime.smallImage = call.body()!!.data.images.jpg.small_image_url
//            infoAnime.bigImage = call.body()!!.data.images.jpg.large_image_url
                val a = call.body()!!
                infoAnime = a.getFullInfoAnimeLG()

            } else {
                Log.d(Constants.TAG, "Error al llamar a API de Jikan")

            }

        return infoAnime
    }
    fun getallTopsAnime(){
        //mostrar una lista con recycler view
        //return "Listado de Animes"
    }


}