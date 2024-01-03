package com.reymon.firstapp.logic.usercases.jikan

import android.util.Log
import com.reymon.firstapp.core.Constants
import com.reymon.firstapp.data.network.endpoints.AnimeEndPoint
import com.reymon.firstapp.data.network.endpoints.RetrofitBase
import com.reymon.firstapp.logic.usercases.jikan.entities.FullInfoAnimeLG

class JikanUserCase {
    fun getFullAnimeInfo(idAnime: Int): FullInfoAnimeLG {
        val baseService = RetrofitBase.getRetrofitJikanConnection()
        val service = baseService.create(AnimeEndPoint::class.java)
        val call = service.getAnimeFullInfo(idAnime) //VOID (UNIT)
        val infoAnime: FullInfoAnimeLG = FullInfoAnimeLG()
        if (call.isSuccessful) {
            //PARA llamar a los datos debemos hacer con una corrutina
            infoAnime.id = call.body()!!.data.mal_id
            infoAnime.name = call.body()!!.data.title_english
            infoAnime.smallImage = call.body()!!.data.images.jpg.small_image_url
            infoAnime.bigImage = call.body()!!.data.images.jpg.large_image_url

        } else {
            Log.d(Constants.TAG, "Error al llamar a API de Jikan")

        }
        return infoAnime
    }


}