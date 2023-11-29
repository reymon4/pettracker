package com.reymon.firstapp.logic.usecases

import com.reymon.firstapp.data.entities.Users
import com.reymon.firstapp.repository.UsersRepository

class SingIn {
    //Esta clase únicamente sirve para devolver el valor del usuario (id)
    fun checkUserPassword(username: String, password: String): Int{
        var ret = -1
        val users = UsersRepository().getListUsers()

        val lstUsers = users.filter { it.password == password && it.userName == username }
        if (lstUsers.isNotEmpty()) {
            ret = lstUsers.first().userId
        }
        //Log.d(Constants.TAG, lstUsers.first().userId.toString())
        return ret
        //return users.contains(Users(username, password))

    }

    fun getUserName(userId: Int): Users =
        UsersRepository().getListUsers().first { it.userId == userId }

}