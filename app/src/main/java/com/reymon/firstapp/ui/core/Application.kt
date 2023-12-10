package com.reymon.firstapp.ui.core

import com.reymon.firstapp.repository.DBRepository
import android.app.Application
class Application: Application(){
    override fun onCreate() {
        super.onCreate()
        //       con = DBConnection().getConnection(applicationContext)
        //       LoginUseCase(con).insertUser()
    }

    companion object{
        private lateinit var con: DBRepository

        fun getConnectionDB():DBRepository?{
            return con
        }
    }
}
