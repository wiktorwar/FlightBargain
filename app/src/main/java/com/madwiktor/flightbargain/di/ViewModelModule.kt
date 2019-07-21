package com.madwiktor.flightbargain.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.madwiktor.flightbargain.ViewModelFactory
import com.madwiktor.flightbargain.ui.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}