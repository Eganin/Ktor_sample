package ru.playzone.features.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import ru.playzone.database.tokens.TokenDto
import ru.playzone.database.tokens.Tokens
import ru.playzone.database.users.UserDto
import ru.playzone.database.users.Users
import ru.playzone.features.register.models.RegisterReceiveRemote
import ru.playzone.features.register.models.RegisterResponseRemote
import ru.playzone.utils.isValidEmail
import java.util.*

class RegisterController(private val call: ApplicationCall) {

    suspend fun registerNewUser() {

        val receive = call.receive<RegisterReceiveRemote>()

        if (!receive.email.isValidEmail()) call.respond(HttpStatusCode.BadRequest, "Email is incorrect")

        val userDto = Users.fetchUser(login = receive.login)

        if (userDto != null) {
            call.respond(HttpStatusCode.Conflict, "User already exists")
        } else {
            val token = UUID.randomUUID().toString()

            try {
                Users.insert(
                    userDto = UserDto(
                        login = receive.login,
                        password = receive.password,
                        email = receive.email,
                        username = ""
                    )
                )
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "User already exists")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Can't create user ${e.localizedMessage}")
            }

            Tokens.insert(
                tokenDto = TokenDto(
                    rowId = UUID.randomUUID().toString(),
                    login = receive.login,
                    token = token
                )
            )

            call.respond(RegisterResponseRemote(token = token))
        }
    }
}