package org.example
import java.io.File

fun caesar(word: String, offset: Int): String {
    return word.map { char ->
        if (char.isLetter()) {
            val base = if (char.isUpperCase()) 'A' else 'a'
            ((char - base + offset) % 26 + base.code).toChar()
        } else char
    }.joinToString("")
}

fun main() {
    val offset = 2
    val inputFile = File("input.txt")
    val outputFile = File("output.txt")

    val encrypted = inputFile.readText()
        .split("\\s+".toRegex()) // separare pe cuvinte
        .map { word ->
            if (word.length in 4..7) caesar(word, offset) else word
        }
        .joinToString(" ")

    outputFile.writeText(encrypted)

}
