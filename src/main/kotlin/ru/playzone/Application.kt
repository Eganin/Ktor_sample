package ru.playzone

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import ru.playzone.features.login.configureLoginRouting
import ru.playzone.features.register.configureRegisterRouting
import ru.playzone.plugins.*

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureLoginRouting()
    configureRegisterRouting()
    configureSerialization()
}
