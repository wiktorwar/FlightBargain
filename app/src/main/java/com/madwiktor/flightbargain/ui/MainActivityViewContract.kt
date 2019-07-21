package com.madwiktor.flightbargain.ui

import com.madwiktor.model.FlightWithCurrency

data class MainActivityViewState(
    val flights: List<FlightWithCurrency>,
    val showLoader: Boolean,
    val errorMessage: String?
)

sealed class MainActivityViewEvent {
    object LoadItems : MainActivityViewEvent()
}