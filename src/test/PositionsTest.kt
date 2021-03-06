package test

import main.utils.writeCollectionContent
import java.io.File
import java.lang.IndexOutOfBoundsException
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

fun main() {
    // checkIfPositionsAreSame19vs20()
    val file = "files/saved/positions.txt"
    val fileContentToUpperCase = Files.newInputStream(Paths.get(file))
        .bufferedReader()
        .lines()
        .map { it.toUpperCase() }
        .toList()
    File(file).writeCollectionContent(fileContentToUpperCase)
}

fun checkIfPositionsAreSame19vs20() {
    //sobre la posición ID
    val fifa19Positions: List<String> = Files
        .readAllLines(Paths.get("files/fifa18/futhead_positions_deprecated.csv"))
    val fifa20Positions: List<String> =
        "ls,st,rs,lw,lf,cf,rf,rw,lam,cam,ram,lm,lcm,cm,rcm,rm,lwb,ldm,cdm,rdm,rwb,lb,lcb,cb,rcb,rb,gk"
            .toUpperCase()
            .split(",")
    print("FIFA19 size -> ${fifa19Positions.size} vs FIFA20 size -> ${fifa20Positions.size}")
    try {
        fifa19Positions.forEach { s -> if (!fifa20Positions.contains(s)) println(s) }
    } catch (e: IndexOutOfBoundsException) {
    }

}