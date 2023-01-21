package ru.playzone.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.playzone.database.tokens.TokenDto
import ru.playzone.database.tokens.Tokens
import ru.playzone.database.users.Users
import ru.playzone.features.login.models.LoginReceiveRemote
import ru.playzone.features.login.models.LoginResponseRemote
import java.util.*

class LoginController(private val call: ApplicationCall) {

    suspend fun performLogin() {
        val receive = call.receive<LoginReceiveRemote>()
        val userDto = Users.fetchUser(login = receive.login)

        userDto?.let {userDto->
            if (userDto.password == receive.password) {
                val token = UUID.randomUUID().toString()
                Tokens.insert(
                    tokenDto = TokenDto(
                        rowId = UUID.randomUUID().toString(),
                        login = receive.login,
                        token = token
                    )
                )
                call.respond(message = LoginResponseRemote(token=token))
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid password")
            }
        } ?: call.respond(HttpStatusCode.BadRequest, "User not found")
    }
}