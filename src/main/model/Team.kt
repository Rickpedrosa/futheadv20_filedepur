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
) : SQLInsert {

    private fun getLogos(): List<String?> {
        val x2 = if (logox2 != null) "'${this.logox2}'" else null
        val x4 = if (logox4 != null) "'${this.logox4}'" else null
        val x6 = if (logox6 != null) "'${this.logox6}'" else null
        return listOf(x2, x4, x6)
    }

    override fun getInsertStatement(): String {
        return "INSERT INTO teams " +
                "(name,id,logox2,logox4,logox6,average,eleven_average,quality) " +
                "VALUES ('${this.name.replace("'", "\\'")}',${this.id}," +
                "${this.getLogos()[0]}," +
                "${this.getLogos()[1]}," +
                "${this.getLogos()[2]}," +
                "${this.average},${this.eleven_average},${this.quality});"
    }

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