package test

import main.model.Player
import main.utils.symbolPosition

fun main(args: Array<String>) {
//    testStringExtension()
//    testSubstrAfterBefore()
    testPlayerBuilding()
}

private fun testStringExtension() {
    val line = "a."
    println("pos es ${line.symbolPosition()}")
}

private fun testSubstrAfterBefore() {
    val line = "\"RW,LS,RAM\"sdsdsdaddada,fwkfwkf,\"fksfks\""
    println(line.symbolPosition(2, '"'))
//    println(line.substringAfterLast("\""))
}

private fun testPlayerBuilding() {
    val it =
        "158023,https://sofifa.com/player/158023/lionel-messi/20/159586,L. Messi,Lionel Andrés Messi Cuccittini,32,1987-06-24,170,72,Argentina,FC Barcelona,94,94,95500000,565000,\"RW, CF, ST\",Left,5,4,4,Medium/Low,Messi,Yes,195800000,\"#Dribbler, #Distance Shooter, #Crosser, #FK Specialist, #Acrobat, #Clinical Finisher, #Complete Forward\",RW,10,,2004-07-01,2021,,,87,92,92,96,39,66,,,,,,,\"Beat Offside Trap, Argues with Officials, Early Crosser, Finesse Shot, Speed Dribbler (CPU AI Only), 1-on-1 Rush, Giant Throw-in, Outside Foot Shot\",88,95,70,92,88,97,93,94,92,96,91,84,93,95,95,86,68,75,68,94,48,40,94,94,75,96,33,37,26,6,11,15,14,8,89+2,89+2,89+2,93+2,93+2,93+2,93+2,93+2,93+2,93+2,93+2,92+2,87+2,87+2,87+2,92+2,68+2,66+2,66+2,66+2,68+2,63+2,52+2,52+2,52+2,63+2\n"
    val player = Player(
        it.substring(0, it.symbolPosition(1)).toLong(),
        it.substring(it.symbolPosition(9) + 1, it.symbolPosition(10)),
        it.substring(it.symbolPosition(2) + 1, it.symbolPosition(3)),
        it.substring(it.symbolPosition(4) + 1, it.symbolPosition(5)).toInt(),
        it.substring(it.symbolPosition(8) + 1, it.symbolPosition(9)),
        it.substring(it.symbolPosition(11) + 1, it.symbolPosition(12)).toInt(),
        it.substring(
            it.symbolPosition(1, '"') + 1,
            it.symbolPosition(2, '"')
        ).split(","),
        it.substring(it.symbolPosition(12) + 1, it.symbolPosition(13)).toLong(),
        it.substring(it.symbolPosition(13) + 1, it.symbolPosition(14)).toLong()
    )
    print(it.substring(it.symbolPosition(14) + 1, it.symbolPosition(14) + 2))
}
