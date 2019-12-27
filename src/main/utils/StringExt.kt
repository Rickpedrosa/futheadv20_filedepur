package main.utils

import main.model.Team

fun String.symbolPosition(symbolNumber: Int = 0, symbol: Char = ','): Int {
    var offset = 0
    var counter = 0
    for ((index, char) in this.withIndex()) {
        if (char == symbol) counter++
        if (counter == symbolNumber) {
            offset = index
            break
        }
    }
    return offset
}

fun String.buildTeam(): Team {
    val id: Int? = if (this.substring(this.indexOf("id="), this.symbolPosition(1)).replace("id=", "") != "null")
        this.substring(this.indexOf("id="), this.symbolPosition(1)).replace("id=", "").toInt() else null
    val logox2: String? = this.substring(this.indexOf("logox2="), this.symbolPosition(6))
        .replace("logox2=", "")
    val logox4: String? = this.substring(this.indexOf("logox4="), this.symbolPosition(7))
        .replace("logox4=", "")
    val logox6: String? = this.substring(this.indexOf("logox6="))
        .replace("logox6=", "")
        .replace(")", "")
    return Team(
        id,
        this.substring(this.indexOf("name="), this.symbolPosition(2))
            .replace("name=", ""),
        this.substring(this.indexOf("average="), this.symbolPosition(3))
            .replace("average=", "").toFloat(),
        this.substring(this.indexOf("eleven_average="), this.symbolPosition(4)).replace(
            "eleven_average=",
            ""
        ).toFloat(),
        this.substring(this.indexOf("quality="), this.symbolPosition(5))
            .replace("quality=", "").toFloat(),
        logox2,
        logox4,
        logox6
    )
}