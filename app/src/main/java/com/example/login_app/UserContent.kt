package com.example.login_app

import java.lang.Exception
import java.util.ArrayList

object UserContent {

    val listOfUsers: MutableList<UserItem> = ArrayList()

    private const val COUNT = 1000

    init {
        // Add my initial user
        listOfUsers.add(UserItem(name = "Tomas", password = "password"))
        // Add some sample items.
        for (i in 0..COUNT) {
            addUser(createUserItem())
        }
    }

    private fun findUserByName(name: String): UserItem? {
        return listOfUsers.find { it.name == name }
    }

    fun registerNewUser(newUser: UserItem) {
        if (newUser.name == "" || newUser.password == "") throw Exception("Usuario o Contraseña vacios")
        if (findUserByName(newUser.name) == null) listOfUsers.add(0, newUser)
        else throw Exception("Nombre de usuario no disponible")
    }

    fun logUserIn(candidateUser: UserItem): Boolean {
        if (candidateUser.name == "" || candidateUser.password == "") throw Exception("Usuario o Contraseña vacios")
        val foundUser = findUserByName(candidateUser.name)
        if (foundUser != null) {
            if (foundUser.password == candidateUser.password) return true
            else throw Exception("Contraseña incorrecta")
        } else throw Exception("Usuario no existe")
    }

    private fun addUser(user: UserItem) {
        listOfUsers.add(user)
    }

    private fun createUserItem(): UserItem {
        return UserItem(
            name = makeRandomUserName(6),
            password = "password"
        )
    }

    private fun makeRandomUserName(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    data class UserItem (
        val id: Long = incrementalId++,
        val name: String,
        val password: String
    ) {
        companion object {
            @JvmStatic
            var incrementalId: Long = 0
        }
    }
}