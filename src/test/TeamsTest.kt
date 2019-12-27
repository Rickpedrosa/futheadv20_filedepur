package test

import main.model.Team
import main.utils.buildTeam
import main.utils.symbolPosition
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream
import kotlin.streams.asSequence

fun main() {
    val sourceOne = "files/testing/teams.txt"
    val sourceTwo = "files/testing/teamsFromSource.txt"
    val fileToWrite = "files/saved/teams.txt"

    val teamsWithId: List<Team> = Files.newInputStream(Paths.get(sourceOne))
        .bufferedReader()
        .lines()
        .flatMap {
            println(it)
            Stream.of(
                it.buildTeam()
            )
        }
        .asSequence()
        .toList()
    val teamsWithoutId: MutableList<Team> = Files.newInputStream(Paths.get(sourceTwo))
        .bufferedReader()
        .lines()
        .flatMap {
            Stream.of(
                it.buildTeam()
            )
        }
        .asSequence()
        .toMutableList()
    teamsWithId.forEach { u ->
        run {
            val teamFound = teamsWithoutId.find { it.name == u.name }
            teamFound?.id = u.id
            teamFound?.logox2 = u.logox2
            teamFound?.logox2 = u.logox4
            teamFound?.logox2 = u.logox6
        }
    }
    teamsWithoutId.toSet().forEach(::println) // todo salen repetidos arreglar
}

private fun readTeams() {
    val fileToBeRead = "files/testing/fifav20_player1.txt"
    val fileToBeWrittenIn = "files/testing/teams.txt"
    val fileToBeWrittenInV2 = "files/testing/teamsFromSource.txt"

    val lines: Set<Team> = Files.newInputStream(Paths.get(fileToBeRead))
        .bufferedReader()
        .lines()
        .skip(1)
        .flatMap { it ->
            getStreamFromPlayerFile(it)
        }
        .asSequence()
        .toSet()
    File(fileToBeWrittenInV2).bufferedWriter().use { out -> lines.forEach { out.write("$it \n") } }
}

private fun getStreamFromToExtractTeamImages(it: String): Stream<Team> {
    val img = it.substring(it.symbolPosition(8) + 1, it.symbolPosition(9))
    val id = img.substring(img.symbolPosition(6, '/') + 1)
        .replace(".png", "")
    return Stream.of(
        Team(
            id.toInt(),
            it.substring(it.symbolPosition(9) + 1, it.symbolPosition(10)),
            0f,
            0f,
            0f
        )
    )
}

private fun getStreamFromPlayerFile(it: String): Stream<Team> {
    return Stream.of(
        Team(
            null,
            it.substring(it.indexOf("club="), it.symbolPosition(2)).replace("club=", ""),
            0f,
            0f,
            0f,
            null,
            null,
            null
        )
    )
}
