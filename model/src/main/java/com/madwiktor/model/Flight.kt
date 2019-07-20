package com.madwiktor.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Flight(
    val id: String,
    val countryFrom: Country,
    val countryTo: Country,
    val dTimeUTC: Long,
    val aTimeUTC: Long,
    val price: Double,
    val mapIdFrom: String,
    val mapIdTo: String,
    val cityFrom: String,
    val cityTo: String,
    @Json(name = "fly_duration") val flyDuration: String,
    @Json(name = "route") val routeList: List<Route>
) {

    fun getImageUrlForDestinationCity(width: Int = 600): String = "https://images.kiwi.com/photos/$width/$mapIdTo.jpg"
}

// https://api.skypicker.com/flights?v=3
// &curr=PLN&sort=popularity&asc=0
// &locale=pl
// &partner=picky&children=0&infants=0&flyFrom=50.0067-19.895-50km
// &featureName=aggregateResults&dateFrom=20/07/2019&dateTo=30/07/2019
// &typeFlight=oneway&one_per_date=0&oneforcity=1&wait_for_refresh=0&adults=1&limit=45