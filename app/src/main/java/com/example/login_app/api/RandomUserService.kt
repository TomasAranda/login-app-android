package com.example.login_app.api

import com.example.login_app.data.RandomUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserService {
    @GET("api/")
    fun getUsers(@Query("results") quantity: Int = 1000) : Call<RandomUserResponse>

    companion object {
        private const val BASE_URL = "https://randomuser.me/"

        fun create(): RandomUserService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RandomUserService::class.java)
        }

        fun getUsers(callback: Callback<RandomUserResponse>) {
            create().getUsers().enqueue(callback)
        }
    }
}