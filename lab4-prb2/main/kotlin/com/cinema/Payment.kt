package com.cinema

data class Payment (
    val amount: Double,
    val paymentMethod: String
) : PaymentProcessor{
    private var isPaid: Boolean = false

    override fun processPayment(): Boolean {
        //Simulare procesare de plata
        isPaid = true
        println("Processing $paymentMethod payment of $amount")
        return isPaid
    }
}
