package com.cinema

import kotlinx.datetime.LocalTime

data class Ticket (
    val movie: Movie,
    val seat: String,
    val price: Double,
    val time: String
) {
    fun getInfo(): String {
        return "Ticket for ${movie.title} at $time, Seat: $seat, Price: $price"
    }
}