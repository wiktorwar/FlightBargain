package com.madwiktor.core

import android.content.SharedPreferences

class SharedPrefsPersistance(private val sharedPreferences: SharedPreferences) : FlightPersistenceStorage {
    override fun saveLatitudeForDate(dateKey: String, latitude: Double) {
        sharedPreferences.edit()
            .putFloat(latitudeKey(dateKey), latitude.toFloat())
            .apply()
    }

    override fun saveLongitudeForDate(dateKey: String, longitude: Double) {
        sharedPreferences.edit()
            .putFloat(longitudeKey(dateKey), longitude.toFloat())
            .apply()
    }

    override fun getLatitudeForDate(dateKey: String): Double? {
        return sharedPreferences
            .getFloat(latitudeKey(dateKey), floatNoValue).let {
                if (it == floatNoValue) null
                else it.toDouble()
            }
    }

    override fun getLongituteForDate(dateKey: String): Double? {
        return sharedPreferences
            .getFloat(longitudeKey(dateKey), floatNoValue).let {
                if (it == floatNoValue) null
                else it.toDouble()
            }
    }

    override fun saveIdsForDateKey(dateKey: String, ids: Set<String>) {
        sharedPreferences.edit()
            .putStringSet(dateKey, ids)
            .apply()
    }

    override fun getIdsForDateKey(dateKey: String): Set<String>? {
        return sharedPreferences.getStringSet(dateKey, null)?.toSet()
    }

    override fun saveShownFlight(id: String, date: String) {
        sharedPreferences.edit()
            .putString(id, date)
            .apply()
    }

    override fun isFlightNeverShown(id: String): Boolean {
        return sharedPreferences.getString(id, null) == null
    }

    private fun longitudeKey(dateKey: String): String = "Long-$dateKey"
    private fun latitudeKey(dateKey: String): String = "Lat-$dateKey"
    private val floatNoValue = Float.MAX_VALUE
}