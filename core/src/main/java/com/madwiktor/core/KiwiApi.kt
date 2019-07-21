package com.madwiktor.core

import com.madwiktor.model.LatLong
import com.madwiktor.model.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface KiwiApi {

    @GET("flights")
    fun searchFlights(
        @Query("flyFrom") flyFrom: LatLong,
        @Query("dateFrom") dateFrom: String,
        @Query("dateTo") dateTo: String,
        @Query("locale") locale: String,
        @Query("curr") currencyCode: String,
        @Query("adults") adultsNumber: Int? = 1,
        @Query("limit") limit: Int? = 50,
        @Query("type") typeFlight: TypeFlight? = TypeFlight.ONE_WAY,
        @Query("one_for_city") oneForCity:Int =1,
        @Query("flyTo") flyTo: String? = null,
        @Query("infants") infantsNumber: Int? = null,
        @Query("children") childrenNumber: Int? = null

    ): Single<SearchResponse>
}

// https://api.skypicker.com/flights?v=3
// &curr=PLN&sort=popularity&asc=0
// &locale=pl
// &partner=picky&children=0&infants=0&flyFrom=50.0067-19.895-50km
// &featureName=aggregateResults&dateFrom=20/07/2019&dateTo=30/07/2019
// &typeFlight=oneway&one_per_date=0&oneforcity=1&wait_for_refresh=0&adults=1&limit=45