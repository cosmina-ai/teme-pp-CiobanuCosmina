package org.example
import java.io.File
import kotlin.math.sqrt

fun distance(p1: Pair<Int, Int>, p2: Pair<Int, Int>): Double {
    val dx = (p1.first - p2.first).toDouble()
    val dy = (p1.second - p2.second).toDouble()
    return sqrt(dx * dx + dy * dy)
}

fun readPointsFromFile(filename: String): List<Pair<Int, Int>> {
    val lines = File(filename).readLines()
    val n = lines.first().toInt()

    return lines.drop(1)
        .take(n)
        .map {
            val (x, y) = it.split(" ").map(String::toInt)
            x to y
        }
}

fun calculatePerimeter(points: List<Pair<Int, Int>>): Double {
    val sideDistances = points.zipWithNext().map { (a, b) -> distance(a, b) }
    val closingDistance = distance(points.last(), points.first())
    return sideDistances.sum() + closingDistance
}

fun main() {
    val points = readPointsFromFile("puncte.txt")
    val perimeter = calculatePerimeter(points)
    println(perimeter.toInt())
}
