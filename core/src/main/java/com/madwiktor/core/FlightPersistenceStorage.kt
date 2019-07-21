package com.madwiktor.core

interface FlightPersistenceStorage {
    fun saveIdsForDateKey(dateKey: String, ids: Set<String>)
    fun getIdsForDateKey(dateKey: String): Set<String>?
    fun saveLatitudeForDate(dateKey: String, latitude: Double)
    fun saveLongitudeForDate(dateKey: String, longitude: Double)
    fun getLatitudeForDate(dateKey: String): Double?
    fun getLongituteForDate(dateKey: String): Double?
    fun saveShownFlight(id:String, date:String)
    fun isFlightNeverShown(id:String):Boolean
}