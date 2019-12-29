package main.model

interface SQLInsert {
    fun getInsertStatement(): String
}