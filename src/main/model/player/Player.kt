package main.model.player

import main.model.SQLInsert
import main.utils.DatabaseExt
import java.sql.ResultSet

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
) : SQLInsert {
    override fun getInsertStatement(): String {
        return "INSERT INTO players " +
                "(id,name,image,nationality,potential,age,club,value,wage) " +
                "VALUES (${this.id},'${this.name.replace("'", "\\'")}'," +
                "'${this.image}','${this.nationality}',${this.potential}," +
                "${this.age},'${this.club.replace("'", "\\'")}'," +
                "${this.value},${this.wage});"
    }

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

fun ResultSet.getPlayersPotentialBySelect(): MutableList<Int> {
    val potentials: MutableList<Int> = mutableListOf()
    while (this.next()) {
        potentials.add(this.getInt(1))
    }
    this.close()
    return potentials
}

val BENCH_POTENTIAL = fun(club: String): String {
    return "SELECT pot FROM " +
            "(SELECT DISTINCT p.name, p.potential AS pot FROM players p WHERE p.club LIKE '${club}' " +
            "ORDER BY pot DESC LIMIT 18) t  ORDER BY pot ASC LIMIT 7"
}

val TITULAR_POTENTIAL = fun(club: String): String {
    return "SELECT pot FROM " +
            "(SELECT DISTINCT p.name, p.potential AS pot FROM players p WHERE p.club LIKE '${club}' " +
            "ORDER BY pot DESC LIMIT 18) t LIMIT 11"
}