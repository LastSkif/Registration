package com.example.registration.data.datasource

import com.example.registration.data.dao.UserDao
import com.example.registration.data.dto.UserDto

interface UserDataSource {

    suspend fun insert(userDto: UserDto)
    fun getData(): UserDto
}

class UserDataSourceImpl(
    private val dao: UserDao
) : UserDataSource {

    override suspend fun insert(userDto: UserDto) {
        dao.insert(userDto)
    }

    override fun getData(): UserDto = dao.getNames()

}