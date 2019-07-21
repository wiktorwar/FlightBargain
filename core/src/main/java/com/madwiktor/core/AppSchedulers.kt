package com.madwiktor.core

import io.reactivex.Scheduler

interface AppSchedulers {
    val io: Scheduler
    val main: Scheduler
    val computation: Scheduler
}