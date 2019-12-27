@file:Suppress("DuplicatedCode")

package main.depure.tryOne

import main.model.Player
import main.utils.symbolPosition
import java.io.*
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream
import kotlin.streams.asSequence
import kotlin.streams.toList

fun main() {
    readPlayers()
}

private fun readPlayers() {
    val lines: Sequence<Player> = Files.newInputStream(Paths.get("files/source/fifa.txt"))
        .bufferedReader()
        .lines()
        .skip(1)
        .flatMap {
            println(it)
            Stream.of(
                Player(
                    it.substring(0, it.symbolPosition(1)).toLong(),
                    it.substring(it.symbolPosition(9) + 1, it.symbolPosition(10)),
                    it.substring(it.symbolPosition(2) + 1, it.symbolPosition(3)),
                    it.substring(it.symbolPosition(4) + 1, it.symbolPosition(5)).toInt(),
                    it.substring(it.symbolPosition(8) + 1, it.symbolPosition(9)),
                    it.substring(it.symbolPosition(11) + 1, it.symbolPosition(12)).toInt(),
                    if (it.substring(it.symbolPosition(14) + 1, it.symbolPosition(14) + 2) == "\"") {
                        it.substring(
                            it.symbolPosition(1, '"') + 1,
                            it.symbolPosition(2, '"')
                        ).split(",")
                    } else {
                        it.substring(it.symbolPosition(14) + 1, it.symbolPosition(15))
                            .split(",")
                    }
                    ,
                    it.substring(it.symbolPosition(12) + 1, it.symbolPosition(13)).toLong(),
                    it.substring(it.symbolPosition(13) + 1, it.symbolPosition(14)).toLong()
                )
            )
        }
        .asSequence()
    File("files/source/fifaTest.txt").bufferedWriter().use { out -> lines.forEach() { out.write("$it \n") } }
}


