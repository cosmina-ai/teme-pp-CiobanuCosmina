fun String.toPascalCase(): String =
    this.split(" ")
        .joinToString("") { it.replaceFirstChar { c -> c.uppercase() } }

class MapFunctor<T, U>(val map: MutableMap<T, U>) {
    fun map(function: (U) -> U): MapFunctor<T, U> {
        val newMap = map.mapValues { (_, value) -> function(value) }.toMutableMap()
        return MapFunctor(newMap)
    }
}

fun main() {
    val originalMap = mutableMapOf(
        1 to "hello world",
        2 to "functional programming",
        3 to "kotlin lambda"
    )

    val result = MapFunctor(originalMap)
        .map { "Test $it" }         // adaugă prefixul
        .map { it.toPascalCase() }  // aplică PascalCase

    println("Rezultat:")
    result.map.forEach { (k, v) -> println("$k: $v") }
}
