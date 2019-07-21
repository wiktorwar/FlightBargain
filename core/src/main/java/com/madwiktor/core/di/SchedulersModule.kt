package com.madwiktor.core.di

import com.madwiktor.core.AppSchedulers
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Singleton

@Module
class SchedulersModule {

    @Provides
    @Singleton
    fun providesAppSchedulers(): AppSchedulers {
        return object : AppSchedulers {
            override val io: Scheduler
                get() = Schedulers.io()
            override val main: Scheduler
                get() = AndroidSchedulers.mainThread()
            override val computation: Scheduler
                get() = Schedulers.computation()

        }
    }

}