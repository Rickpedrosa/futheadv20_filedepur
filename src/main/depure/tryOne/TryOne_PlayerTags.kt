package main.depure.tryOne

import main.utils.symbolPosition
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream
import kotlin.streams.asSequence

fun main() {
    readPlayerTags()
}

private fun readPlayerTags() {
    //coma 23
    // " 3 - 4
    val fileToBeRead = "files/source/fifa.txt"
    val fileToBeWrittenIn = "files/saved/player_tags.txt"

    val lines: Set<String> = Files.newInputStream(Paths.get(fileToBeRead))
        .bufferedReader()
        .lines()
        .skip(1)
        .flatMap {
            Stream.of(
                it.split(",")
            )
        }
        .flatMap { it ->
            Stream.of(it.map {
                it.trim()
            }.filter { it.startsWith("#") })
        }
        .filter { it.isNotEmpty() }
        .flatMap { it ->
            it.stream().map {
                it.replace('"', ' ')
            }
        }
        .asSequence().toSet()
    File(fileToBeWrittenIn).bufferedWriter().use { out -> lines.forEach { out.write("$it \n") } }
}