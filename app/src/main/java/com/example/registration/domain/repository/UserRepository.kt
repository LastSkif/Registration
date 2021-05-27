package com.example.registration.domain.repository

import com.example.registration.domain.entity.User

interface UserRepository {

    suspend fun insert(user: User)
    suspend fun getNames(): User
}