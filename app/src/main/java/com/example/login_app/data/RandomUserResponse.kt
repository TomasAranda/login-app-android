package com.example.login_app.data

import java.io.Serializable

data class RandomUserResponse(
    val results: List<RandomUser>
)

data class RandomUser (
    val email: String,
    val gender: String,
    val name: RandomUserName,
    val picture: RandomUserPicture
)

data class RandomUserName (
    val title: String,
    val first: String,
    val last: String
)

data class RandomUserPicture (
    val large: String
)

data class RandomUserArgument (
    val name: String?,
    val email: String?,
    val imageUrl: String?
) : Serializable
