package com.reymon.firstapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.reymon.firstapp.data.local.entities.Users


@Dao
interface UsersDAO {


    @Query("select * from Users")
    fun getAllUsers():List<Users>

    @Query("select * from Users where userId= :userId")
    fun getOneUser(userId: Int): Users

    @Insert
    fun insertUser(users: List<Users>)

    @Update
    fun updateUsers(users: List<Users>)
    //Deber delete
}