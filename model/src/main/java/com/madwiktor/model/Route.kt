package com.madwiktor.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Route (val id:String,
                  val cityFrom:String,
                  val cityTo:String)
