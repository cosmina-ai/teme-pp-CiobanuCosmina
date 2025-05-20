package org.example

fun main() {
    val lista = listOf(1, 21, 75, 39, 7, 2, 35, 3, 31, 7, 8)

    val rezultat = lista
        .filter { it >= 5 } // Eliminăm numerele mai mici decât 5
        .chunked(2)         // Grupăm în perechi
        .map { it[0] * it[1] } // Înmulțim fiecare pereche
        .reduce { acc, elem -> acc + elem } // Adunăm rezultatele

    println("Rezultatul final este: $rezultat")
}
