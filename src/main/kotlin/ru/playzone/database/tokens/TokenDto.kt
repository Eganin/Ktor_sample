package ru.playzone.database.tokens

import kotlinx.serialization.Serializable

@Serializable
data class TokenDto(
    val rowId: String,
    val login: String,
    val token: String
)
