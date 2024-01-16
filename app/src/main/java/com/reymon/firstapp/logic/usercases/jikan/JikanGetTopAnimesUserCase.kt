package com.reymon.firstapp.logic.usercases.jikan

import android.util.Log
import com.reymon.firstapp.core.Constants
import com.reymon.firstapp.data.network.endpoints.TopAnimesEndPoint

import com.reymon.firstapp.data.network.entities.top.TopAnimesDataClass
import com.reymon.firstapp.data.network.repository.RetrofitBase

class JikanGetTopAnimesUserCase {
    suspend fun getResponse(): Result<TopAnimesDataClass> {

        var result:Result<TopAnimesDataClass>? = null
        var infoAnime = TopAnimesDataClass()
        try {
            val baseService = RetrofitBase.getRetrofitJikanConnection()
            val service = baseService.create(TopAnimesEndPoint::class.java)
            val call = service.getAllTopAnimes() //VOID (UNIT)

            if (call.isSuccessful) {
//            //PARA llamar a los datos debemos hacer con una corrutina
//            infoAnime.id = call.body()!!.data.mal_id
//            infoAnime.name = call.body()!!.data.title_english
//            infoAnime.smallImage = call.body()!!.data.images.jpg.small_image_url
//            infoAnime.bigImage = call.body()!!.data.images.jpg.large_image_url
                val a = call.body()!!
                infoAnime = a
                result = Result.success(infoAnime)


            } else {
                Log.d(Constants.TAG, "Error al llamar a API de Jikan")
                result = Result.failure(Exception("Error al llamar a API de Jikan"))
            }

        } catch (ex: Exception) {
            Log.e(Constants.TAG, ex.stackTraceToString())
            result = Result.failure(Exception(ex))
        }

        return result!!
    }


}