package com.example.registration.data.database

import android.content.Context
import androidx.room.Room

class UserDatabaseBuilder {

    private companion object {

        const val USER_DATABASE_NAME = "user"
    }

    fun build(context: Context): UserDatabase =
        Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            USER_DATABASE_NAME,
        ).build()
}