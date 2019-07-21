package com.madwiktor.flightbargain

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.madwiktor.flightbargain.di.AppComponent
import com.madwiktor.flightbargain.di.AppModule
import com.madwiktor.flightbargain.di.DaggerAppComponent
import timber.log.Timber

class App : Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        AndroidThreeTen.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}