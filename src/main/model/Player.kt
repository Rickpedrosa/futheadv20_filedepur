package main.model

import main.utils.symbolPosition

data class Player(
    val id: Long, //blank - 1+1
    val club: String, //9+1 - 10
    val name: String, //2+1 - 3
    val age: Int, //4+1 - 5
    val nationality: String, //8+1 - 9
    val potential: Int, //11+1 - 12
    val positions: List<String>, //14+1 - 15
    val value: Long, //12+1 - 13
    val wage: Long, //13+1 - 14
    val image: String = "https://cdn.sofifa.org/players/10/20/$id.png"
)  // todo añadir mas mierda
{
    override fun toString(): String {
        return "{\"id\":${this.id}," +
                "\"club\":\"${this.club}\"," +
                "\"name\":\"${this.name}\"," +
                "\"age\":${this.age}," +
                "\"nationality\":\"${this.nationality}\"," +
                "\"potential\":${this.potential}," +
                "\"positions\":${this.positions}," +
                "\"value\":${this.value}," +
                "\"wage\":${this.wage}," +
                "\"image\":\"${this.image}\"}"
    }
}

interface PlayerBuilder {
    fun getId(): Long
    fun getClub(): String
    fun getName(): String
    fun getAge(): Int
    fun getNationality(): String
    fun getPotential(): Int
    fun getPositions(): List<String>
    fun getValue(): Long
    fun getWage(): Long
}

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