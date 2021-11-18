package com.example.login_app

import java.lang.Exception
import java.util.ArrayList

object UserContent {

    val listOfUsers: MutableList<UserItem> = ArrayList()

    private val COUNT = 1000

    init {
        // Add some sample items.
        listOfUsers.add(UserItem(name = "Tomás", password = "password"))
        for (i in 0..COUNT) {
            addUser(createUserItem())
        }
    }

    private fun findUserByName(name: String): UserItem? {
        return listOfUsers.find { it.name == name }
    }

    fun registerNewUser(newUser: UserItem) {
        if (newUser.name == "" || newUser.password == "") throw Exception("Empty username or password")
        if (findUserByName(newUser.name) == null) listOfUsers.add(0, newUser)
        else throw Exception("Username already taken")
    }

    fun logUserIn(candidateUser: UserItem): Boolean {
        if (candidateUser.name == "" || candidateUser.password == "") throw Exception("Empty username or password")
        val foundUser = findUserByName(candidateUser.name)
        if (foundUser != null) {
            if (foundUser.password == candidateUser.password) return true
            else throw Exception("Wrong password")
        } else throw Exception("Username not found")
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