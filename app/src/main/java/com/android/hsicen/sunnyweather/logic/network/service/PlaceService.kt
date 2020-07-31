package com.android.hsicen.sunnyweather.logic.network.service

import com.android.hsicen.sunnyweather.SunnyApplication
import com.android.hsicen.sunnyweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 作者：hsicen  2020/7/30 8:50
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：SunnyWeather
 */

interface PlaceService {

    @GET("v2/place?token=${SunnyApplication.TOKEN}&lang=zh_CN")
    fun searchPlace(@Query("query") query: String): Call<PlaceResponse>
}