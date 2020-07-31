package com.android.hsicen.sunnyweather

import android.app.Application
import android.content.Context

/**
 * 作者：hsicen  2020/7/30 8:33
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：SunnyWeather
 */
class SunnyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        ctx = applicationContext
    }

    companion object {
        const val TOKEN = "MWDQ9X3gsHlOdXCi"

        //提供全局Context
        lateinit var ctx: Context
    }
}