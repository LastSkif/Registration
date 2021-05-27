package com.example.registration.data.mapper

import com.example.registration.data.dto.UserDto
import com.example.registration.domain.entity.User

fun User.toDto(): UserDto = UserDto(
    name = name,
    family = family,
)

fun UserDto.toEntity(): User = User(
    name = name,
    family = family,
)