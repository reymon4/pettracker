package com.reymon.firstapp.logic.usercases.local

import com.reymon.firstapp.data.local.entities.Users
import com.reymon.firstapp.data.local.repository.DBRepository
import com.reymon.firstapp.data.local.repository.UserRepository

class LoginUserCase (val connection: DBRepository){
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

    suspend fun getUserName(usrId: Int): Users =
        connection.getUsersDAO().getOneUser(usrId)

    suspend fun getUserName1(usrId:Int) : Users =
        UserRepository().getListUsers().first{
            it.userId == usrId
        }

    suspend fun insertUser()=
        if(connection.getUsersDAO().getAllUsers().isNotEmpty()){
            connection.getUsersDAO().insertUser(
                UserRepository().getListUsers()
            )
        }else{

        }

    suspend fun getAllUsers(): List<Users> {
        return connection.getUsersDAO().getAllUsers()
        //Llamamos al DAo
    }

}