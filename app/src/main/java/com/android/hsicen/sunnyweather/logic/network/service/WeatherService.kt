package com.android.hsicen.sunnyweather.logic.network.service

import com.android.hsicen.sunnyweather.SunnyApplication
import com.android.hsicen.sunnyweather.logic.model.DailyResponse
import com.android.hsicen.sunnyweather.logic.model.RealTimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 作者：hsicen  2020/7/31 8:22
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：SunnyWeather
 */
interface WeatherService {

    @GET("v2.5/${SunnyApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(
        @Path("lng") lng: String,
        @Path("lat") lat: String
    ): Call<RealTimeResponse>

    @GET("v2.5/${SunnyApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<DailyResponse>
}
