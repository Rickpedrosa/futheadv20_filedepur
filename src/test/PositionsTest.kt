package test

import java.lang.IndexOutOfBoundsException
import java.nio.file.Files
import java.nio.file.Paths

fun main() {
   // checkIfPositionsAreSame19vs20()
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