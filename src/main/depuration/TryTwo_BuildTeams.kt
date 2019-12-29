package main.depuration

import com.google.gson.Gson
import main.model.Team
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream
import kotlin.streams.asSequence

//fun main() {
//    readTeamsV2()
//}

private fun readTeamsV2() {
    val source = "files/saved/teams.txt"
    val allTeams: List<Team> = Files.newInputStream(Paths.get(source))
        .bufferedReader()
        .lines()
        .flatMap {
            Stream.of(Gson().fromJson<Team>(it, Team::class.java))
        }
        .asSequence()
        .toList()
    allTeams.forEach { println(it.name) }
}