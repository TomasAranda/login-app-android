package com.example.login_app.data

import androidx.room.*

@Dao
interface UserDao {
        @Query("SELECT * FROM users")
        suspend fun getAll(): List<User>

        @Query("SELECT * FROM users WHERE id = (:userId)")
        fun findById(userId: Int): User

        @Insert
        fun insert(user: User)

        @Insert
        suspend fun insertAll(users: List<User>)

        @Delete
        fun delete(user: User)
}