package main.model

data class Team(
    var id: Int?,
    val name: String,
    var average: Float,
    var eleven_average: Float,
    var quality: Float,
    var logox2: String? = "https://cdn.sofifa.org/teams/2/light/$id.png",
    var logox4: String? = "https://cdn.sofifa.org/teams/4/light/$id.png",
    var logox6: String? = "https://cdn.sofifa.org/teams/6/light/$id.png"
) {
    override fun equals(other: Any?): Boolean {
        other as Team
        return other.name == this.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
