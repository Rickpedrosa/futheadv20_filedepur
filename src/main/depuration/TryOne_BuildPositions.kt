package main.depuration

import main.model.DataSource
import main.model.player.Player
import main.utils.writeCollectionContent
import java.io.File
import java.util.stream.Stream
import kotlin.streams.asSequence

fun main() {
    createFileFromPositionsPlayer()
    println("Finished!")
}


private fun createFileFromPositionsPlayer() {
    println("Working!")
    val file = "files/saved/player_positions.txt"
    val associations: List<String> = DataSource.players
        .stream()
        .flatMap { Stream.of(getPositionsPerPlayer(it)) }
        .map { it.trim() }
        .asSequence()
        .toList()
    File(file).writeCollectionContent(associations)
}

private fun getPositionsPerPlayer(player: Player): String {
    var assoc = ""
    for (pos in player.positions) {
        assoc += "${player.id},$pos\n"
    }
    return assoc
}