package com.madwiktor.model

import io.reactivex.Observable
import org.threeten.bp.LocalDate
import java.util.*

interface Repository {
    fun getBargainFlights(fromDate: LocalDate, toDate: LocalDate, locale: Locale): Observable<FlightQueryResult>
}