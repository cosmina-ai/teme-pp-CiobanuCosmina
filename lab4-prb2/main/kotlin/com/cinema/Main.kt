package com.cinema

fun main() {
    // Initialize booking system
    val bookingSystem = BookingSystem()

    // Add some movies
    bookingSystem.addMovie(Movie("The Matrix", 136, "R"))
    bookingSystem.addMovie(Movie("Inception", 148, "PG-13"))
    bookingSystem.addMovie(Movie("Interstellar", 169, "PG-13"))

    // Create a customer
    val customer = Customer("John Doe", "john@example.com", "1234567890")

    // Find a movie
    val movie = bookingSystem.findMovie("The Matrix")
    if (movie != null) {
        // Create tickets
        val tickets = listOf(
            Ticket(movie, "A1", 12.50, "18:00"),
            Ticket(movie, "A2", 12.50, "18:00")
        )

        // Create booking
        val booking = bookingSystem.createBooking(customer, tickets, "Credit Card")

        // Confirm booking
        if (booking.confirmBooking()) {
            println("Booking confirmed successfully!")
            println(booking.getBookingDetails())
        } else {
            println("Booking failed!")
        }
    } else {
        println("Movie not found!")
    }

    // List all movies
    println("\nAvailable Movies:")
    bookingSystem.listMovies().forEach { println(it.getInfo()) }
}