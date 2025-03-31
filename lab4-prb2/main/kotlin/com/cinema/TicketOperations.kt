package com.cinema

interface TicketOperations {
    fun calculateTotal(): Double
    fun confirmBooking(): Boolean
}