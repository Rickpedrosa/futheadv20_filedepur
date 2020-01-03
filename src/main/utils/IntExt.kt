package main.utils

fun Int.between(minValueInclusive: Int, maxValueInclusive: Int): Boolean {
    return (this in minValueInclusive..maxValueInclusive)
}