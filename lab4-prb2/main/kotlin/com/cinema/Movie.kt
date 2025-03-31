package com.cinema

data class Movie(
    val title: String,
    val duration: Int,
    val rating: String
) : MovieInfo {
    override fun getInfo(): String {
        return "Movie: $title, Duration: $duration minutes, Rating: $rating"
    }
}
