package ru.playzone.database.users

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val login: String,
    val password: String,
    val email: String?,
    val username: String
)
