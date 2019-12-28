@file:Suppress("DuplicatedCode")

package main.depure

import main.utils.writeCollectionContent
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream
import kotlin.streams.asSequence
import kotlin.streams.toList

//fun main() {
////    readPlayerTags()
//    trimPlayerTags()
//}

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
    File(fileToBeWrittenIn).writeCollectionContent(lines)
}

private fun trimPlayerTags() {
    val file = "files/saved/player_tags.txt"
    val tags: List<String> = Files.newInputStream(Paths.get(file))
        .bufferedReader()
        .lines()
        .map { it.trim() }
        .toList()
    tags.forEach(::println)
    File(file).writeCollectionContent(tags)
}