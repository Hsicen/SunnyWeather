package com.android.hsicen.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * 作者：hsicen  2020/7/30 8:46
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：SunnyWeather
 */

data class Location(val lng: String, val lat: String)

data class Place(
    val name: String, val location: Location,
    @SerializedName("formatted_address")
    val address: String
)

data class PlaceResponse(val status: String, val place: List<Place>)