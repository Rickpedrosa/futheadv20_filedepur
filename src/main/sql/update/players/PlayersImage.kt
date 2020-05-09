package main.sql.update.players

import main.model.DataSource
import main.utils.DatabaseExt

fun main() {
    val database = DatabaseExt()
    DataSource.players.forEach {
        database.statement.executeUpdate(
            getQueryStatementToSetPlayerImages(it.id)
        )
    }
    database.closeConnection()
}

private fun getQueryStatementToSetPlayerImages(id: Long): String {
    val idToString = id.toString()
    val firstImagePart = if (idToString.length == 6) idToString.substring(0, 3) else "0${idToString.substring(0, 2)}"
    val secondImagePart = if (idToString.length == 6) idToString.substring(3) else idToString.substring(2)
    val image = "https://cdn.sofifa.com/players/${firstImagePart}/${secondImagePart}/20_120.png"
    return "UPDATE players SET image = '$image' WHERE id = $id"
}