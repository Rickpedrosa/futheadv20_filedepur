package main.sql.insert

import main.model.DataSource
import main.utils.DatabaseExt
import main.utils.symbolPosition
import main.utils.writeCollectionContent
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.stream.Stream
import kotlin.streams.toList

fun main() {
    println("Go!")
    val insert = Inserter
//    insert.insertPlayers()
//    insert.insertPlayerPositions()
//    insert.insertPositions()
//    insert.insertTeams()
    println("DOne!")
}

object Inserter {

    enum class Tables {
        POSITIONS, TEAMS, PLAYER_POSITIONS, PLAYERS
    }


    fun addFileContentToDatabase(filePath: String) {
        println("Start -> ${Date()}")
        val database = DatabaseExt()
        Files.newInputStream(Paths.get(filePath))
            .bufferedReader()
            .lines().forEach {
                database.batch.addBatch(it)
            }
        database.batch.executeBatch()
        database.closeConnection()
        println("Finish -> ${Date()}")
    }

    private fun insert(file: String, table: Tables) {
        val fileToBeWrittenIn = File(file)
        when (table) {
            Tables.POSITIONS -> fileToBeWrittenIn.writeCollectionContent(getCollectionToInsert(Tables.POSITIONS))
            Tables.TEAMS -> fileToBeWrittenIn.writeCollectionContent(getCollectionToInsert(Tables.TEAMS))
            Tables.PLAYER_POSITIONS -> fileToBeWrittenIn.writeCollectionContent(getCollectionToInsert(Tables.PLAYER_POSITIONS))
            Tables.PLAYERS -> fileToBeWrittenIn.writeCollectionContent(getCollectionToInsert(Tables.PLAYERS))
        }
    }

    private fun getCollectionToInsert(origin: Tables): List<String> = when (origin) {
        Tables.POSITIONS -> {
            DataSource.positions
                .stream()
                .flatMap {
                    Stream.of(
                        "INSERT INTO positions (position) VALUES" +
                                " ('${it.replace(",", "")}');"
                    )
                }
                .toList()
        }
        Tables.TEAMS -> {
            DataSource.teams
                .stream()
                .flatMap {
                    Stream.of(it.getInsertStatement())
                }
                .toList()
        }
        Tables.PLAYER_POSITIONS -> {
            DataSource.playerPositions
                .stream()
                .flatMap {
                    Stream.of(
                        "INSERT INTO playerpositions " +
                                "(player_id,pos) " +
                                "VALUES (${it.substring(0, it.symbolPosition(1))}," +
                                "'${it.substring(it.symbolPosition(1) + 1)}');"
                    )
                }
                .toList()
        }
        Tables.PLAYERS -> {
            DataSource.players
                .stream()
                .flatMap {
                    Stream.of(it.getInsertStatement())
                }
                .toList()
        }
    }

    fun insertPositions() {
        val fileToWrite =
            "files/fifa20/insert/futhead_positions.sql"
        insert(fileToWrite, Tables.POSITIONS)
    }

    fun insertPlayers() {
        val fileToWrite =
            "files/fifa20/insert/futhead_players.sql"
        insert(fileToWrite, Tables.PLAYERS)
    }

    fun insertTeams() {
        val fileToWrite =
            "files/fifa20/insert/futhead_teams.sql"
        insert(fileToWrite, Tables.TEAMS)
    }

    fun insertPlayerPositions() {
        val fileToWrite =
            "files/fifa20/insert/futhead_player_positions.sql"
        insert(fileToWrite, Tables.PLAYER_POSITIONS)
    }
}

