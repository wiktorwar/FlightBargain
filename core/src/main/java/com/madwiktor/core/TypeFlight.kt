package com.madwiktor.core

enum class TypeFlight(private val networkName: String) {
    ONE_WAY("oneway");

    override fun toString(): String {
        return networkName
    }
}