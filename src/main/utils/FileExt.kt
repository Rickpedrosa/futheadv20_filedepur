package main.utils

import java.io.File

fun <T> File.writeCollectionContent(collection: Sequence<T>) {
    this.bufferedWriter().use { out -> collection.forEach { out.write("$it\n") } }
}

fun <T> File.writeCollectionContent(collection: List<T>) {
    this.bufferedWriter().use { out -> collection.forEach { out.write("$it\n") } }
}

fun <T> File.writeCollectionContent(collection: Set<T>) {
    this.bufferedWriter().use { out -> collection.forEach { out.write("$it\n") } }
}