package com.madwiktor.model

sealed class FlightQueryResult {
    data class Success(val searchResponse: SearchResponse)
    data class Error(val message: String)
}