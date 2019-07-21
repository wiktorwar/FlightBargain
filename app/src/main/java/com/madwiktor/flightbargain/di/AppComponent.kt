package com.madwiktor.flightbargain.di

import com.madwiktor.core.di.NetworkModule
import com.madwiktor.core.di.RepoModule
import com.madwiktor.core.di.SchedulersModule
import com.madwiktor.flightbargain.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RepoModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        SchedulersModule::class]
)
interface AppComponent {
    fun inject(targe: MainActivity)
}