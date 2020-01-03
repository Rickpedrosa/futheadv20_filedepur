package main.sql.insert.team

import main.model.DataSource
import main.model.player.BENCH_POTENTIAL
import main.model.player.TITULAR_POTENTIAL
import main.model.player.getPlayersPotentialBySelect
import main.utils.DatabaseExt
import main.utils.between

fun main() {
    val database = DatabaseExt()
    DataSource.teams.forEach {
        database.statement.executeUpdate(
            getQueryStatementToSetTeamQuality(database, it.name.replace("'", "\\'"))
        )
    }
    database.closeConnection()
}

private fun getQueryStatementToSetTeamQuality(database: DatabaseExt, club: String): String {
    val globalMedia: Float
    var avgTeam = 0f
    val quality: Float

    val resultSetMedia = database.select(
        "SELECT AVG(lol) FROM " +
                "(SELECT DISTINCT p.name AS xd, p.potential AS lol" +
                " FROM players p WHERE p.club LIKE '${club}' ORDER BY p.potential DESC LIMIT 18) t"
    )
    globalMedia = if (resultSetMedia.next()) resultSetMedia.getFloat(1) else 0f
    resultSetMedia.close()
    println("Media Global de $club es $globalMedia")


    val benchPlayers: MutableList<Int> = getPlayersPotential(
        database, BENCH_POTENTIAL(club)
    )
    benchPlayers.forEach { if (it > globalMedia) avgTeam += ((it - globalMedia) / 2) }


    val titularPlayers: MutableList<Int> = getPlayersPotential(
        database, TITULAR_POTENTIAL(club)
    )
    titularPlayers.forEach { if (it > globalMedia) avgTeam += (it - globalMedia) }
    avgTeam = globalMedia + (avgTeam * 0.1f)
    println("Media AVG (18 mejores jugadores) de $club es $avgTeam")
    println("${globalMedia.toInt()}")

    val media = globalMedia.toInt()
    quality = if (media.between(81, 99)) {
        5f
    } else if (media.between(77, 80)) {
        4.5f
    } else if (media == 75 || media == 76) {
        4f
    } else if (media.between(70, 74)) {
        3.5f
    } else if (media.between(63, 69)) {
        3f
    } else if (media == 64 || media == 65) {
        2.5f
    } else if (media == 62 || media == 63) {
        2f
    } else if (media == 60 || media == 61) {
        1.5f
    } else if (media.between(57, 59)) {
        1f
    } else if (media.between(0, 56)) {
        0.5f
    } else {
        0f
    }
    return "UPDATE teams SET average = $globalMedia, eleven_average = $avgTeam, quality = $quality" +
            " WHERE name LIKE '${club}'"
}

private fun getPlayersPotential(database: DatabaseExt, sql: String): MutableList<Int> {
    return database.select(sql).getPlayersPotentialBySelect()
}
