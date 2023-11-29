package com.reymon.firstapp.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.reymon.firstapp.data.dao.UsersDAO
import com.reymon.firstapp.data.entities.Users

//Le decimos que sea abstract para que no se pueda instanciar
@Database(entities = [Users::class],
    version = 1)
abstract class DBRepository : RoomDatabase(){
    //Creo funciones para cada DAO que tenga
    abstract fun getUsersDAO() : UsersDAO


}