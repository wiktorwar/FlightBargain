package com.madwiktor.model

sealed class FlightQueryResult {
    data class Success(val flights: List<FlightWithCurrency>) : FlightQueryResult()
    data class Error(val message: String) : FlightQueryResult()
}