package com.example.registration.data.repository

import com.example.registration.data.datasource.UserDataSource
import com.example.registration.data.mapper.toDto
import com.example.registration.data.mapper.toEntity
import com.example.registration.domain.entity.User
import com.example.registration.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val userDataSource: UserDataSource
) : UserRepository {

    override suspend fun insert(user: User) {
        withContext(Dispatchers.IO) {
            userDataSource.insert(user.toDto())
        }
    }

    override suspend fun getNames(): User =
        withContext(Dispatchers.IO) {
            userDataSource.getData().toEntity()
        }
}