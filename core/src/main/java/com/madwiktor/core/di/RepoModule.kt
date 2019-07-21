package com.madwiktor.core.di

import android.content.Context
import android.location.Location
import com.madwiktor.core.*
import com.madwiktor.model.LatLong
import com.madwiktor.model.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    private val prefsName = "flightsPrefs"
    @Provides
    @Singleton
    fun providesFlightPersistenceStorage(context: Context): FlightPersistenceStorage {
        return SharedPrefsPersistance(context.getSharedPreferences(prefsName, Context.MODE_PRIVATE))
    }

    @Provides
    @Singleton
    fun providesRepository(
        kiwiApi: KiwiApi, appSchedulers: AppSchedulers,
        flightPersistenceStorage: FlightPersistenceStorage
    ): Repository {
        return RepositoryImplementation(kiwiApi, appSchedulers, flightPersistenceStorage, distanceChecker)
    }

    private val distanceChecker: (Pair<LatLong, LatLong>) -> Double = { (first, second) ->
        val location1 = Location("1")
            .apply {
                latitude = first.lat
                longitude = first.long
            }
        val location2 = Location("2")
            .apply {
                latitude = second.lat
                longitude = second.long
            }
        location1.distanceTo(location2).toDouble()
    }
}