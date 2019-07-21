package com.madwiktor.flightbargain.ui

import android.location.Location
import android.location.LocationManager
import com.madwiktor.model.FlightQueryResult
import com.madwiktor.model.FlightWithCurrency
import com.madwiktor.model.LatLong
import com.madwiktor.model.Repository
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Observable
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.threeten.bp.LocalDate
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class MainActivityViewModelTest {

    @Mock
    lateinit var repo: Repository
    @Mock
    lateinit var locationManager: LocationManager
    lateinit var tested: MainActivityViewModel

    @Mock
    lateinit var location: Location

    private val lat = 50.0
    private val long = 19.0
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        tested = MainActivityViewModel(locationManager, repo)
        whenever(locationManager.getLastKnownLocation(any())).thenReturn(location)
        whenever(location.latitude).thenReturn(lat)
        whenever(location.longitude).thenReturn(long)
    }

    @Test
    fun `after rotation state should be replayed and network shouldn't be called`() {
        val mockList = mock<List<FlightWithCurrency>>()
        whenever(repo.getBargainFlights(any(),any(), any(),any()))
            .thenReturn(
                Observable.just(FlightQueryResult.Success(mockList)))
        val screen1Test = tested.viewState.test()
        //initial state
        assertEquals(MainActivityViewState(emptyList(),false,null),screen1Test.values()[0])

        verify(repo, times(1)).getBargainFlights(LatLong(lat,long), LocalDate.now(), Locale.getDefault())
        //loading
        assertEquals(MainActivityViewState(emptyList(),true,null),screen1Test.values()[1])
        //loaded list from network
        assertEquals(MainActivityViewState(mockList,false,null),screen1Test.values()[2])
        assertEquals(3,screen1Test.values().size)

        //simulate rotation
        screen1Test.dispose()
        //new activity is subscribing to vm
        val screen2Test= tested.viewState.test()
        //state should be replayed
        assertEquals(MainActivityViewState(mockList,false,null),screen2Test.values().first())
        assertEquals(1,screen2Test.values().size)
        // repo shouldn't be called again
        verify(repo, times(1)).getBargainFlights(LatLong(lat,long), LocalDate.now(), Locale.getDefault())
    }
}