package com.cinema

class Booking (
    val customer: Customer,
    private val tickets: List<Ticket>
): TicketOperations{
    private lateinit var payment: Payment

    fun setPayment(payment: Payment){
        this.payment = payment
    }

    override fun calculateTotal(): Double {
        return tickets.sumOf { it.price }
    }

    override fun confirmBooking(): Boolean {
        val total = calculateTotal()
        println("Confirming booking for ${customer.name}")
        println("Total amount: $total")
        return payment.processPayment()
    }

    fun getBookingDetails(): String {
        return """
            Customer: ${customer.name}
            Tickets:
            ${tickets.joinToString("\n") { it.getInfo() }}
            Total: ${calculateTotal()}
        """.trimIndent()
    }
}