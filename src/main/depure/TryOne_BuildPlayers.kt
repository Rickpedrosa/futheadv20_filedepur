package main.depure

import com.google.gson.Gson
import main.model.player.Player
import main.model.player.PlayerBuilder
import main.model.player.PlayerBuilderImpl
import main.utils.writeCollectionContent
import java.io.*
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream
import kotlin.streams.asSequence

fun main() {
    //readFromSourceAndWriteInFile()
    readPlayers()
}

private fun readFromSourceAndWriteInFile() {
    val version = 1
    val fileToBeRead = "files/source/fifa.txt"
    val fileToBeWrittenIn = "files/testing/fifav20_player${version}.txt"
    val lines: Sequence<Player> = Files.newInputStream(Paths.get(fileToBeRead))
        .bufferedReader()
        .lines()
        .skip(1)
        .flatMap {
            println(it)
            Stream.of(buildPlayer(it))
        }
        .asSequence()
    File(fileToBeWrittenIn).writeCollectionContent(lines)
}

private fun readPlayers() {
    val allPlayers: List<Player> = Files.newInputStream(Paths.get("files/testing/fifav20_player1.txt"))
        .bufferedReader()
        .lines()
        .flatMap {
            Stream.of(Gson().fromJson<Player>(it, Player::class.java))
        }
        .asSequence()
        .toList()
    allPlayers.forEach { println(it.name) }
}

fun buildPlayer(line: String): Player {
    val builder: PlayerBuilder =
        PlayerBuilderImpl(line)
    return Player(
        builder.getId(),
        builder.getClub(),
        builder.getName(),
        builder.getAge(),
        builder.getNationality(),
        builder.getPotential(),
        builder.getPositions(),
        builder.getValue(),
        builder.getWage()
    )
}


