package ru.playzone.features.games

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.playzone.database.games.Games
import ru.playzone.database.games.mapToCreateGameResponse
import ru.playzone.database.games.mapToGameDto
import ru.playzone.features.games.models.CreateGameRequest
import ru.playzone.features.games.models.FetchGameRequest
import ru.playzone.utils.TokenCheck

class GamesController(private val call: ApplicationCall) {

    suspend fun performSearch() {
        val request = call.receive<FetchGameRequest>()
        val token = call.request.headers["Bearer-Authorization"]

        if (TokenCheck.isTokenValid(token = token.orEmpty()) || TokenCheck.isTokenAdmin(token = token.orEmpty())) {
            if (request.searchQuery.isBlank()) {
                call.respond(Games.fetchAll())
            } else {
                call.respond(Games.fetchAll().filter { it.title.contains(request.searchQuery, ignoreCase = true) })
            }
        } else {
            call.respond(HttpStatusCode.Unauthorized, "Token expired")
        }
    }

    suspend fun createGame() {
        val token = call.request.headers["Bearer-Authorization"]
        if (TokenCheck.isTokenAdmin(token = token.orEmpty())) {
            val request = call.receive<CreateGameRequest>()
            val game = request.mapToGameDto()
            Games.insert(gameDto = game)
            call.respond(game.mapToCreateGameResponse())
        } else {
            call.respond(HttpStatusCode.Unauthorized)
        }
    }
}