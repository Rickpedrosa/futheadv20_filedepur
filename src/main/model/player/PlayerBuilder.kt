package main.model.player

interface PlayerBuilder {
    fun getId(): Long
    fun getClub(): String
    fun getName(): String
    fun getAge(): Int
    fun getNationality(): String
    fun getPotential(): Int
    fun getPositions(): List<String>
    fun getValue(): Long
    fun getWage(): Long
}