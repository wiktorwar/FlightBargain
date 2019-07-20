package com.madwiktor.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Country (val code:String,val name:String)