package com.android.hsicen.sunnyweather.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.android.hsicen.sunnyweather.SunnyApplication
import com.android.hsicen.sunnyweather.logic.model.Place
import com.google.gson.Gson

/**
 * 作者：hsicen  2020/7/31 9:06
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：存储搜索的位置
 */
object PlaceDao {
    //存储的xml文件名
    private const val NAME = "sunny_weather"

    fun savePlace(place: Place) {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getPlace(): Place {
        val placeStr = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeStr, Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() =
        SunnyApplication.ctx.getSharedPreferences(NAME, Context.MODE_PRIVATE)
}