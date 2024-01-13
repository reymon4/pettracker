package com.reymon.firstapp.data.local.repository

import com.reymon.firstapp.data.local.entities.Users


class UserRepository {
    fun getListUsers(): List<Users>{
        return listOf<Users>(
            Users("admin", "admin", 1, "Reymon", "Hidalgo"),
            Users( "jose", "jose",2),
            Users("lucho", "lucho",3)

        )

    }
}
