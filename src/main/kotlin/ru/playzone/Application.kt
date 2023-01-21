package ru.playzone

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.Database
import ru.playzone.features.games.configureGamesRouting
import ru.playzone.features.login.configureLoginRouting
import ru.playzone.features.register.configureRegisterRouting
import ru.playzone.plugins.configureRouting
import ru.playzone.plugins.configureSerialization

fun main() {
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/postgres",
        driver = "org.postgresql.Driver",
        password = "Zakharin0479",
        user = "postgres"
    )

    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureLoginRouting()
    configureRegisterRouting()
    configureGamesRouting()
    configureSerialization()
}
