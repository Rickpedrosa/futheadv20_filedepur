package main.model.player

import main.utils.symbolPosition

class PlayerBuilderImpl(private val line: String) : PlayerBuilder {
    //line es cada linea del fichero files/source/fifa.txt
    override fun getId(): Long {
        return line.substring(0, line.symbolPosition(1)).toLong()
    }

    override fun getClub(): String {
        return line.substring(line.symbolPosition(9) + 1, line.symbolPosition(10))
    }

    override fun getName(): String {
        return line.substring(line.symbolPosition(2) + 1, line.symbolPosition(3))
    }

    override fun getAge(): Int {
        return line.substring(line.symbolPosition(4) + 1, line.symbolPosition(5)).toInt()
    }

    override fun getNationality(): String {
        return line.substring(line.symbolPosition(8) + 1, line.symbolPosition(9))
    }

    override fun getPotential(): Int {
        return line.substring(line.symbolPosition(11) + 1, line.symbolPosition(12)).toInt()
    }

    override fun getPositions(): List<String> {
        return if (line.substring(
                line.symbolPosition(14) + 1,
                line.symbolPosition(14) + 2
            ) == "\""
        ) {
            line.substring(
                line.symbolPosition(1, '"') + 1,
                line.symbolPosition(2, '"')
            ).split(",")
        } else {
            line.substring(line.symbolPosition(14) + 1, line.symbolPosition(15))
                .split(",")
        }
    }

    override fun getValue(): Long {
        return line.substring(line.symbolPosition(12) + 1, line.symbolPosition(13)).toLong()
    }

    override fun getWage(): Long {
        return line.substring(line.symbolPosition(13) + 1, line.symbolPosition(14)).toLong()
    }
}