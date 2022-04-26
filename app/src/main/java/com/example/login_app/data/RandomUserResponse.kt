package com.example.login_app.data

data class RandomUserResponse(
    val results: List<RandomUser>
)

data class RandomUser (
    val email: String,
    val gender: String
)

