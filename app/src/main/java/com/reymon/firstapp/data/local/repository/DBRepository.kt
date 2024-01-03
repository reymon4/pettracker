package com.reymon.firstapp.data.local.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.reymon.firstapp.data.local.dao.UsersDAO
import com.reymon.firstapp.data.local.entities.Users

//Le decimos que sea abstract para que no se pueda instanciar
@Database(entities = [Users::class],
    version = 1)
abstract class DBRepository : RoomDatabase(){
    //Creo funciones para cada DAO que tenga
    abstract fun getUsersDAO() : UsersDAO


}
class DBConnection(){
    fun getConnection(context: Context) : DBRepository =
        Room.databaseBuilder(
            context,
            DBRepository::class.java,
            "DBTest"
        ).build()

}
