package com.cinema

class BookingSystem {
    private val movies = mutableListOf<Movie>()
    private val bookings = mutableListOf<Booking>()

    fun addMovie(movie: Movie) {
        movies.add(movie)
    }

    fun findMovie(title: String): Movie? {
        return movies.find { it.title.equals(title, ignoreCase = true) }
    }

    fun createBooking(customer: Customer, tickets: List<Ticket>, paymentMethod: String): Booking {
        val total = tickets.sumOf { it.price }
        val payment = Payment(total, paymentMethod)
        val booking = Booking(customer, tickets).apply {
            setPayment(payment)
        }
        bookings.add(booking)
        return booking
    }

    fun listMovies(): List<Movie> {
        return movies.toList()
    }

    fun listBookings(): List<Booking> {
        return bookings.toList()
    }
}