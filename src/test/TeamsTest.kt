package test

import main.model.Team
import main.utils.symbolPosition
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream
import kotlin.streams.asSequence

fun main() {

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
