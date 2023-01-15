package ru.playzone.database.tokens

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Tokens : Table(){
    private val id = Tokens.varchar(name = "id", length = 50)
    private val login = Tokens.varchar(name = "login", length = 25)
    private val token = Tokens.varchar(name = "token", length = 50)

    fun insert(tokenDto: TokenDto){
        transaction {
            Tokens.insert {
                it[login]= tokenDto.login
                it[id]= tokenDto.rowId
                it[token]= tokenDto.token
            }
        }
    }
}