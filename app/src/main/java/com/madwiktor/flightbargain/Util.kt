package com.madwiktor.flightbargain

import android.content.Context

fun Context.appComponent() = (this.applicationContext as App).appComponent
