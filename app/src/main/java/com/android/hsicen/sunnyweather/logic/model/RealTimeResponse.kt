package com.android.hsicen.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * 作者：hsicen  2020/7/31 8:23
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：实时天气数据
 */


data class RealTimeResponse(val status: String, val result: Result) {

    data class Result(val realTime: RealTime)

    data class RealTime(
        val skycon: String, val temperature: Float,
        @SerializedName("air_quality")
        val airQuality: AirQuality
    )

    data class AirQuality(val aqi: AQI)

    data class AQI(val chn: Float)
}
