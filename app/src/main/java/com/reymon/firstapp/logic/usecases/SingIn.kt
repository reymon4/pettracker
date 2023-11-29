package com.reymon.firstapp.logic.usecases

import com.reymon.firstapp.data.entities.Users
import com.reymon.firstapp.repository.UserRepository

class SingIn {
    //Esta clase únicamente sirve para devolver el valor del usuario (id)
    fun checkUserandPassword(user: String, password: String): Int {
        var ret = -1
        var users = UserRepository().getListUsers()
        val exist = users.filter {
            it.userName.equals(user) and it.password.equals(password)
        }
        if (exist.isNotEmpty()) {
            ret = exist.first().userId
        }
        return ret
    }

    fun getUserName(usrId: Int): Users =
        UserRepository().getListUsers().first {
            it.userId.equals(usrId)
        }


}