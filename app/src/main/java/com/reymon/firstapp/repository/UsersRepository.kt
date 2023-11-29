package com.reymon.firstapp.repository

import com.reymon.firstapp.data.entities.Users


public class UsersRepository {
    fun getListUsers(): List<Users>{
        return listOf<Users>(
            Users("admin", "admin", 1, "Reymon", "Hidalgo"),
            Users( "jose", "jose",2),
            Users("lucho", "lucho",3)

        )

    }
}
