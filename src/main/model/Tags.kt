package main.model

import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

class Tags {
    companion object {
        val source = Files.newInputStream(Paths.get("files/saved/player_tags.txt"))
            .bufferedReader()
            .lines()
            .toList()
    }
}