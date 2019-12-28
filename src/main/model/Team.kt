package main.model


data class Team(
    var id: Int?,
    val name: String,
    var average: Float,
    var eleven_average: Float,
    var quality: Float,
    var logox2: String? = if (id != null) "https://cdn.sofifa.org/teams/2/light/$id.png" else null,
    var logox4: String? = if (id != null) "https://cdn.sofifa.org/teams/4/light/$id.png" else null,
    var logox6: String? = if (id != null) "https://cdn.sofifa.org/teams/6/light/$id.png" else null
)  {
    override fun equals(other: Any?): Boolean {
        other as Team
        return other.name == this.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun toString(): String {
        val logox2: String? = if (this.id != null) "\"${this.logox2}\"" else null
        val logox4: String? = if (this.id != null) "\"${this.logox4}\"" else null
        val logox6: String? = if (this.id != null) "\"${this.logox6}\"" else null
        return "{\"id\":${this.id}," +
                "\"name\":\"${this.name}\"," +
                "\"average\":${this.average}," +
                "\"eleven_average\":${this.eleven_average}," +
                "\"quality\":${this.quality}," +
                "\"logox2\":${logox2}," +
                "\"logox4\":${logox4}," +
                "\"logox6\":${logox6}}"
    }
}
