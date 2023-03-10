package ru.playzone.database.games

import kotlinx.serialization.Serializable
import ru.playzone.features.games.models.CreateGameRequest
import ru.playzone.features.games.models.CreateGameResponse
import java.util.*

@Serializable
data class GameDto(
    val gameId: String,
    val title: String,
    val description: String,
    val version: String,
    val size: Double
)

fun CreateGameRequest.mapToGameDto(): GameDto =
    GameDto(
        gameId = UUID.randomUUID().toString(),
        title = title,
        description = description,
        version = version,
        size = size
    )

fun GameDto.mapToCreateGameResponse(): CreateGameResponse =
    CreateGameResponse(
        gameID = gameId,
        title = title,
        description = description,
        version = version,
        size = size
    )