package com.madwiktor.core

import com.madwiktor.model.FlightQueryResult
import com.madwiktor.model.FlightWithCurrency
import com.madwiktor.model.LatLong
import com.madwiktor.model.Repository
import io.reactivex.Observable
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

class RepositoryImplementation(
    private val kiwiApi: KiwiApi,
    private val appSchedulers: AppSchedulers,
    private val flightPersistenceStorage: FlightPersistenceStorage,
    private val distanceCalculator: (Pair<LatLong, LatLong>) -> Double
) : Repository {
    private val apiDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    override fun getBargainFlights(
        forLocation: LatLong,
        forDate: LocalDate,
        locale: Locale,
        numberOfFlights: Int
    ): Observable<FlightQueryResult> {
        return alreadyShownIdsOfFlights(forDate, forLocation)
            .flatMap { setOfIdsAlreadyShown ->
                kiwiApi
                    .searchFlights(
                        forLocation,
                        dateFrom = apiDateFormatter.format(forDate),
                        dateTo = apiDateFormatter.format(forDate.plusDays(7)),
                        locale = locale.country, currencyCode = Currency.getInstance(locale).currencyCode
                    )
                    .map { searchResponse ->
                        val flightsAlreadyShown = searchResponse.data.filter {
                            setOfIdsAlreadyShown.contains(it.id)
                        }
                        val numberOfFlightsThatMustBeAdded = numberOfFlights - flightsAlreadyShown.size
                        val flightsToShow = flightsAlreadyShown
                            .plus(searchResponse.data
                                .filter { flightPersistenceStorage.isFlightNeverShown(it.id) }
                                .take(numberOfFlightsThatMustBeAdded))
                            .map { FlightWithCurrency(it, searchResponse.currency) }

                        saveDisplayedFlightIds(forDate, flightsToShow, forLocation)

                        FlightQueryResult.Success(flightsToShow) as FlightQueryResult
                    }

                    .onErrorReturn { FlightQueryResult.Error(it.localizedMessage) }
                    .toObservable()
            }
            .observeOn(appSchedulers.main)
    }

    private fun saveDisplayedFlightIds(
        forDate: LocalDate,
        flightsToShow: List<FlightWithCurrency>,
        forLocation: LatLong
    ) {
        val dateKey = apiDateFormatter.format(forDate)
        flightPersistenceStorage.saveIdsForDateKey(dateKey, flightsToShow.map { it.flight.id }.toSet())
        flightPersistenceStorage.saveLatitudeForDate(dateKey, forLocation.lat)
        flightPersistenceStorage.saveLongitudeForDate(dateKey, forLocation.long)
        flightsToShow.forEach {
            flightPersistenceStorage.saveShownFlight(it.flight.id, dateKey)
        }
    }

    private fun alreadyShownIdsOfFlights(
        forDate: LocalDate,
        forLocation: LatLong
    ): Observable<Set<String>> {
        return Observable.fromCallable {
            val savedValuesForTheDate =
                flightPersistenceStorage.getIdsForDateKey(apiDateFormatter.format(forDate))
            val (lastLat, lastLong) = flightPersistenceStorage.let {
                val datekey = apiDateFormatter.format(forDate)
                Pair(
                    it.getLatitudeForDate(datekey),
                    it.getLongituteForDate(datekey)
                )
            }
            when {
                savedValuesForTheDate == null -> emptySet()
                lastLat == null || lastLong == null -> emptySet()
                else -> {
                    val lastLatLong = LatLong(lastLat, lastLong)
                    val distance = distanceCalculator(Pair(forLocation, lastLatLong))
                    if (distance < forLocation.toleranceKm)
                        savedValuesForTheDate
                    else
                        emptySet()
                }
            }
        }
            .subscribeOn(appSchedulers.io)
    }
}