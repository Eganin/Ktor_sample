package ru.playzone.database.users

data class UserDto(
    val login: String,
    val password: String,
    val email: String?,
    val username: String
)
