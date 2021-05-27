package com.example.registration.domain.usecase

import com.example.registration.domain.entity.User
import com.example.registration.domain.repository.UserRepository

class InsertUserUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(user: User) {
        userRepository.insert(user)
    }
}