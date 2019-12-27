package main.utils

fun String.symbolPosition(symbolNumber: Int = 0, symbol: Char = ','): Int {
    var offset = 0
    var counter = 0
    for ((index, char) in this.withIndex()) {
        if (char == symbol) counter++
        if (counter == symbolNumber) {
            offset = index
            break
        }
    }
    return offset
}