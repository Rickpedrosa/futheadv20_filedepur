package main.sql.update.teams

import main.model.DataSource
import main.utils.DatabaseExt

fun main() {
    val database = DatabaseExt()
    DataSource.teams.forEach {
        if (it.id !== null) {
            database.statement.executeUpdate(
                getQueryStatementToSetTeamImages(
                    it.id,
                    it.name.replace("'", "\\'")
                )
            )
        }
    }
    database.closeConnection()
}

private fun getQueryStatementToSetTeamImages(id: Int?, club: String?): String {
    val logox2 = "https://cdn.sofifa.com/teams/${id}/light_60.png"
    val logox4 = "https://cdn.sofifa.com/teams/${id}/light_120.png"
    val logox6 = "https://cdn.sofifa.com/teams/${id}/light_240.png"
    return "UPDATE teams SET logox2 = '${logox2}', logox4 = '${logox4}', logox6 = '${logox6}'" +
            " WHERE name LIKE '${club}'"
}