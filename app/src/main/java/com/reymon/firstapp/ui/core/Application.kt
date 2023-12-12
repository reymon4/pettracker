package com.reymon.firstapp.ui.core

import com.reymon.firstapp.repository.DBRepository
import android.app.Application
import com.reymon.firstapp.logic.usecases.SingIn
import com.reymon.firstapp.repository.DBConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Application: Application(){
    override fun onCreate() {
        super.onCreate()

        //Ambiente de corrutina, dispatcher (entrada y salida porque vamos a conectar a la DB)
        //
        GlobalScope.launch(Dispatchers.IO) {
            var con = DBConnection().getConnection(applicationContext)
            SingIn(con).insertUser()

        }
    }
    companion object{
        private lateinit var con: DBRepository

        fun getConnectionDB():DBRepository?{
            return con
        }
    }

}
