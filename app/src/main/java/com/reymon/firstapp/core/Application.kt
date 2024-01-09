package com.reymon.firstapp.core

import com.reymon.firstapp.data.local.repository.DBRepository
import android.app.Application
import com.reymon.firstapp.logic.usercases.local.LoginUserCase
import com.reymon.firstapp.data.local.repository.DBConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Application: Application(){
    override fun onCreate() {
        super.onCreate()

        //Ambiente de corrutina, dispatcher (entrada y salida porque vamos a conectar a la DB)
        con = DBConnection().getConnection(applicationContext)
        GlobalScope.launch(Dispatchers.IO) {

            LoginUserCase(con).insertUser()

        }
    }
    companion object{
        private lateinit var con: DBRepository

        fun getConnectionDB(): DBRepository?{
            return con
        }
    }

}