package main.model

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
) // todo añadir mas mierda