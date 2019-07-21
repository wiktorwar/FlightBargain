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
    val mapIdfrom: String,
    val mapIdto: String,
    val cityFrom: String,
    val cityTo: String,
    @Json(name = "fly_duration") val flyDuration: String,
    @Json(name = "route") val routeList: List<Route>
) {

    fun getImageUrlForDestinationCity(): String = "https://images.kiwi.com/photos/1280x720/$mapIdto.jpg"
}