package test

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import main.model.player.Player
import main.model.Team

fun main() {
    val gson = GsonBuilder().setPrettyPrinting().create()
//    testTeamGson(gson)
   // print("Pog'loler".replace("'", "\\'"))
    val idCristiano = 20801
    val idJoao = 242444
    println(idCristiano.toString().substring(0,2))
    println(idCristiano.toString().substring(2))
    println(idJoao.toString().substring(0,3))
    println(idJoao.toString().substring(3))
}

private fun testPlayerGson(gson: Gson) {
    val player: Player = Player(
        244561,
        "Morecambe",
        "L. Jagne",
        21,
        "England",
        61,
        listOf("CM", "CDM"),
        50000,
        1000,
        "https://cdn.sofifa.org/players/10/20/244561.png"
    )
    val copyplayer = gson.fromJson<Player>(player.toString(), Player::class.java)
    print(player == copyplayer)
}

private fun testTeamGson(gson: Gson) {
    val team: Team = Team(
        null,
        "xd",
        0.0f,
        0.0f,
        0.0f
    )

    print(team.toString())

}