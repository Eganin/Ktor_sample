package ru.playzone.features.login.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginReceiveRemote(
    val login: String,
    val password: String
)

@Serializable
data class LoginResponseRemote(
    val token: String
)