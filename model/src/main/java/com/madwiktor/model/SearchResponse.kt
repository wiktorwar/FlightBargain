package com.madwiktor.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse (val data:List<Flight>, val currency:String)