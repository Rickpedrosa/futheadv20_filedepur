package main.model

import com.google.gson.Gson
import main.model.player.Player
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream
import kotlin.streams.asSequence
import kotlin.streams.toList

class DataSource {
    companion object {
        val tags: List<String> = Files.newInputStream(Paths.get("files/saved/player_tags.txt"))
            .bufferedReader().lines().toList()
        val players: List<Player> = Files.newInputStream(Paths.get("files/saved/players.txt"))
            .bufferedReader()
            .lines()
            .flatMap {
                Stream.of(Gson().fromJson<Player>(it, Player::class.java))
            }.asSequence().toList()
        val teams: List<Team> = Files.newInputStream(Paths.get("files/saved/teams.txt"))
            .bufferedReader()
            .lines()
            .flatMap {
                Stream.of(Gson().fromJson<Team>(it, Team::class.java))
            }.asSequence().toList()
        val positions = Files.newInputStream(Paths.get("files/saved/positions.txt"))
            .bufferedReader().lines().map { it.toUpperCase() }.toList()
        val playerPositions = Files.newInputStream(Paths.get("files/saved/player_positions.txt"))
            .bufferedReader().lines().asSequence().toList()
    }
}