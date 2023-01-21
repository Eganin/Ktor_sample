package ru.playzone.features.games

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureGamesRouting() {
    routing {
        post("/games/search") {
            GamesController(call = call).performSearch()
        }

        post("/games/create") {
            GamesController(call = call).createGame()
        }
    }
}