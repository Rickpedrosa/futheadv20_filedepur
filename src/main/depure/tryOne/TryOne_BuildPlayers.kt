package main.depure.tryOne

import main.model.Player
import main.model.PlayerBuilder
import main.model.PlayerBuilderImpl
import main.utils.writeCollectionContent
import java.io.*
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream
import kotlin.streams.asSequence

fun main() {
    readPlayers()
}

private fun readPlayers() {
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

fun buildPlayer(line: String): Player {
    val builder: PlayerBuilder = PlayerBuilderImpl(line)
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


