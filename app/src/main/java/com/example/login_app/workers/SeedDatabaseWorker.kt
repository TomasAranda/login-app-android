package com.example.login_app.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.login_app.api.RandomUserService
import com.example.login_app.data.AppDatabase
import com.example.login_app.data.RandomUser
import com.example.login_app.data.RandomUserResponse
import com.example.login_app.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            RandomUserService.getUsers(object : Callback<RandomUserResponse> {
                override fun onResponse(
                    call: Call<RandomUserResponse>,
                    response: Response<RandomUserResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        runBlocking {
                            launch {
                                val database = AppDatabase.getInstance(applicationContext)
                                val userList = response.body()!!.results.toUserList()
                                database.userDao().insertAll(userList)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<RandomUserResponse>, t: Throwable) {
                    Log.e("API CALL ERROR", "${t.message}")
                    t.printStackTrace()
                }
            })

            Result.success()
        } catch (ex: Exception) {
            Log.e("SEED DATABASE", "Error seeding database", ex)
            Result.failure()
        }
    }
}

private fun List<RandomUser>.toUserList(): List<User> {
    return this.map {
        User(
            it.name.first,
            it.name.last,
            it.email,
            it.picture.large
        )
    }
}
