package com.madwiktor.model

import io.reactivex.Observable
import org.threeten.bp.LocalDate
import java.util.*

interface Repository {
    /** Returns five attractive flight offers */
    fun getBargainFlights(
        forLocation: LatLong,
        forDate: LocalDate,
        locale: Locale,
        numberOfFlights: Int = 5
    ): Observable<FlightQueryResult>
}