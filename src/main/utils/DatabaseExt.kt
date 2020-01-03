package main.utils

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class DatabaseExt(
    private val connection: Connection = DriverManager.getConnection(
        "jdbc:mysql://localhost/futheadv20?characterEncoding=utf8",
        "root",
        ""
    ),
    val statement: Statement = connection.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_UPDATABLE
    )
) {
    var select = fun(sql: String): ResultSet {
        return statement.executeQuery(sql)
    }
    val batch: Statement = connection.createStatement()
    fun closeConnection() {
        connection.close()
    }
}