package com.madwiktor.model

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

data class LatLong(val lat: Double, val long: Double, val toleranceKm: Int = 70) {

    override fun toString(): String {
        val otherSymbols = DecimalFormatSymbols(Locale.getDefault())
        otherSymbols.decimalSeparator = '.'
        val df = DecimalFormat("#.####", otherSymbols)
        return "${df.format(lat)}-${df.format(long)}-${toleranceKm}km"
    }
}
