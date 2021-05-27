package com.example.registration.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.registration.data.dao.UserDao
import com.example.registration.data.dto.UserDto

@Database(
    entities = [
        UserDto::class
    ],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
}