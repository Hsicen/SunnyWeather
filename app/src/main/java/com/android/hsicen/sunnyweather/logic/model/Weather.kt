package com.android.hsicen.sunnyweather.logic.model

/**
 * 作者：hsicen  2020/7/31 8:32
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：SunnyWeather
 */

data class Weather(val realTime: RealTimeResponse.RealTime, val daily: DailyResponse.Daily)
