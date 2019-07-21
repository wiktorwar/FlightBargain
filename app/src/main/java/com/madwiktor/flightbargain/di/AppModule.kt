package com.madwiktor.flightbargain.di

import android.app.Application
import android.content.Context
import android.location.LocationManager
import dagger.Module
import dagger.Provides

@Module
class AppModule(app: Application) {
    private val appContext = app.applicationContext

    @Provides
    fun providesAppContext(): Context = appContext

    @Provides
    fun providesLocationManager(): LocationManager {
        return appContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }
}