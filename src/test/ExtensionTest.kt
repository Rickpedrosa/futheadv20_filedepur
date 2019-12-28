package test

import main.model.player.Player
import main.model.DataSource
import main.utils.symbolPosition

fun main(args: Array<String>) {
//    testStringExtension()
//    testSubstrAfterBefore()
    // testPlayerBuilding()
//    testingTags()
    DataSource.tags.forEach(::println)
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

private fun testingTags() {
    val it =
        "200389,https://sofifa.com/player/200389/jan-oblak/20/159586,J. Oblak,Jan Oblak,26,1993-01-07,188,87,Slovenia,Atlético Madrid,91,93,77500000,125000,GK,Right,3,3,1,Medium/Medium,Normal,Yes,164700000,,GK,13,,2014-07-16,2023,GK,1,,,,,,,87,92,78,89,52,90,\"Flair, Acrobatic Clearance\",13,11,15,43,13,12,13,14,40,30,43,60,67,88,49,59,78,41,78,12,34,19,11,65,11,68,27,12,18,87,92,78,90,89,,,,,,,,,,,,,,,,,,,,,,,,,,\n"
    val messi =
        "158023,https://sofifa.com/player/158023/lionel-messi/20/159586,L. Messi,Lionel Andrés Messi Cuccittini,32,1987-06-24,170,72,Argentina,FC Barcelona,94,94,95500000,565000,\"RW, CF, ST\",Left,5,4,4,Medium/Low,Messi,Yes,195800000,\"#Dribbler, #Distance Shooter, #Crosser, #FK Specialist, #Acrobat, #Clinical Finisher, #Complete Forward\",RW,10,,2004-07-01,2021,,,87,92,92,96,39,66,,,,,,,\"Beat Offside Trap, Argues with Officials, Early Crosser, Finesse Shot, Speed Dribbler (CPU AI Only), 1-on-1 Rush, Giant Throw-in, Outside Foot Shot\",88,95,70,92,88,97,93,94,92,96,91,84,93,95,95,86,68,75,68,94,48,40,94,94,75,96,33,37,26,6,11,15,14,8,89+2,89+2,89+2,93+2,93+2,93+2,93+2,93+2,93+2,93+2,93+2,92+2,87+2,87+2,87+2,92+2,68+2,66+2,66+2,66+2,68+2,63+2,52+2,52+2,52+2,63+2\n"

    val tag = messi.substring(messi.symbolPosition(23), messi.symbolPosition(23) + 1)
    println(tag)
}