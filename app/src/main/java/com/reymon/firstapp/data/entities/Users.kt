package com.reymon.firstapp.data.entities

data class Users(

    val userName: String? = null,
    val password: String? = null,
) {
    var userId = -1
    var firstName: String = "No registrado"
    var lastName: String = ""
    var profile: String = ""

    constructor(userName: String?, password: String?, userId: Int) : this(
        userName,
        password
    ) {
        this.userId = userId
    }
    constructor(id: Int, userName: String?, password: String?, profile: String) : this(
        userName,
        password
    ) {
        this.profile = profile
    }
    constructor(
        userName: String?,
        password: String?,
        userId: Int,
        firstName: String,
        lastName: String
    ) : this(
        userName,
        password
    ) {
        this.userId = userId
        this.firstName = firstName
        this.lastName = lastName
    }

}
