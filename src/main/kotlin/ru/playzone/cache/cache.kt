package ru.playzone.cache

import ru.playzone.features.register.models.RegisterReceiveRemote

data class TokenCache(
    val login: String,
    val token: String
)

object InMemoryCache{
    val userList: MutableList<RegisterReceiveRemote> = mutableListOf()
    val token: MutableList<TokenCache> = mutableListOf()
}