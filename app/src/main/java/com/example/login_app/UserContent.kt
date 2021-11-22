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

    fun registerNewUser(username: String, password: String): Boolean {
        if (username == "" || password == "") throw EmptyUsernameOrPasswordException("Usuario o Contraseña vacios")
        if (findUserByName(username) == null) {
            listOfUsers.add(0, createUserItem(username, password))
            return true
        }
        else throw UsernameAlreadyTakenException("Nombre de usuario no disponible")
    }

    fun logUserIn(candidateUsername: String, candidatePassword: String): Boolean {
        if (candidateUsername == "" || candidatePassword == "") throw EmptyUsernameOrPasswordException("Usuario o Contraseña vacios")
        val foundUser = findUserByName(candidateUsername)
        if (foundUser != null) {
            if (foundUser.password == candidatePassword) return true
            else throw WrongPasswordException("Contraseña incorrecta")
        } else throw UsernameDoesNotExistException("Usuario no existe")
    }

    private fun addUser(user: UserItem) {
        listOfUsers.add(user)
    }

    private fun createUserItem(username: String = makeRandomUserName(6), password: String = "password"): UserItem {
        return UserItem(
            name = username,
            password = password
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