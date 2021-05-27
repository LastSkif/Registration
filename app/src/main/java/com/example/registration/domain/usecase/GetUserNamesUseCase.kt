package com.example.registration.domain.usecase

import com.example.registration.domain.entity.User
import com.example.registration.domain.repository.UserRepository

class GetUserNamesUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): User =
        userRepository.getNames()
}