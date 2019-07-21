package com.madwiktor.flightbargain.ui

import android.annotation.SuppressLint
import android.location.LocationManager
import androidx.lifecycle.ViewModel
import com.madwiktor.model.FlightQueryResult
import com.madwiktor.model.FlightWithCurrency
import com.madwiktor.model.LatLong
import com.madwiktor.model.Repository
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.threeten.bp.LocalDate
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val locationManager: LocationManager,
    private val repository: Repository
) : ViewModel() {

    val viewState: Observable<MainActivityViewState>

    private sealed class Result {
        object Loading : Result()
        data class FligtsLoaded(val flights: List<FlightWithCurrency>) : Result()
        data class Error(val error: String) : Result()
    }

    private val viewEventEmitter: PublishSubject<MainActivityViewEvent> = PublishSubject.create()

    init {
        viewState = viewEventEmitter
            .startWith(MainActivityViewEvent.LoadItems)
            .doOnNext {
                Timber.d("$it")
            }
            .toResult()
            .resultToViewState()
            .replay(1)
            .autoConnect()
            .doOnNext { Timber.d("View state $it") }

    }


    fun proccessEvent(viewEvent: MainActivityViewEvent) {
        viewEventEmitter.onNext(viewEvent)
    }

    @SuppressLint("MissingPermission")
    private fun Observable<MainActivityViewEvent>.toResult(): Observable<Result> {
        return cast(MainActivityViewEvent.LoadItems::class.java) // it's only one view event, we can cast
            .flatMap {
                val latLong = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                    .let { LatLong(it.latitude, it.longitude) }
                repository.getBargainFlights(latLong, LocalDate.now(), Locale.getDefault())
                    .map {
                        when (it) {
                            is FlightQueryResult.Success -> Result.FligtsLoaded(it.flights)
                            is FlightQueryResult.Error -> Result.Error(it.message)
                        }
                    }
                    .startWith(Result.Loading)
            }
    }

    private fun Observable<Result>.resultToViewState(): Observable<MainActivityViewState> {
        return scan(MainActivityViewState(emptyList(), false, null))
        { vs, result ->
            val newState = when (result) {
                Result.Loading -> vs.copy(showLoader = true)
                is Result.FligtsLoaded -> vs.copy(showLoader = false, flights = result.flights, errorMessage = null)
                is Result.Error -> vs.copy(showLoader = false, errorMessage = result.error)
            }
            newState
        }
    }
}