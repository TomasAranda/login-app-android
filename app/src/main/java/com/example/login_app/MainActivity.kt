package com.example.login_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.login_app.data.AppDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchDB()
    }

    // Access to DB to instantiate it (Calls Room.onCreate callback if isn't created already)
    private fun fetchDB() {
        Log.v("FIRST ACCESS TO DB", AppDatabase.getInstance(applicationContext).toString())
        // TODO("Implement dagger @Provides for DB instantiation")
    }
}