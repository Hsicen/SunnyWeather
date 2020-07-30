package com.android.hsicen.sunnyweather.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 作者：hsicen  2020/7/30 8:55
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：SunnyWeather
 */
object ServiceCreator {
    private const val BASE_URL = "https://api.caiyunapp.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(service: Class<T>): T = retrofit.create(service)

    //内联函数，泛型实例化
    inline fun <reified T> create(): T = create(T::class.java)
}
