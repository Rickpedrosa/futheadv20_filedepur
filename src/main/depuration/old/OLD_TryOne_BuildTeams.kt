package main.depuration.old

import main.model.Team
import main.utils.buildTeam
import main.utils.writeCollectionContent
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream
import kotlin.streams.asSequence


private fun createTeams() {
    val sourceOne = "files/testing/teams.txt"
    val sourceTwo = "files/testing/teamsFromSource.txt"
    val fileToWrite = "files/saved/teams.txt"

    val teamsWithId: List<Team> = Files.newInputStream(Paths.get(sourceOne))
        .bufferedReader()
        .lines()
        .flatMap {
            //            println(it)
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
        val teamFound = teamsWithoutId.find {
            it.name == u.name
        }
        teamFound?.id = u.id
        teamFound?.logox2 = u.logox2
        teamFound?.logox4 = u.logox4
        teamFound?.logox6 = u.logox6
    }
    File(fileToWrite).writeCollectionContent(teamsWithoutId)
}