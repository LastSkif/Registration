package com.example.registration.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.registration.data.dto.UserDto

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userDto: UserDto)

    @Query("SELECT * FROM user")
    fun getNames(): UserDto
}