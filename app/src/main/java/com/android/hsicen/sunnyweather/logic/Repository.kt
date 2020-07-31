package com.android.hsicen.sunnyweather.logic

import androidx.lifecycle.liveData
import com.android.hsicen.sunnyweather.logic.dao.PlaceDao
import com.android.hsicen.sunnyweather.logic.model.Place
import com.android.hsicen.sunnyweather.logic.model.Weather
import com.android.hsicen.sunnyweather.logic.network.Net
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

/**
 * 作者：hsicen  2020/7/30 9:13
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：仓库层，供ViewModel层使用，用于发起网络请求获取数据，然后回调给ViewModel层
 * 获取数据的逻辑：从本地获取或者从网络获取
 */
object Repository {

    fun searchPlace(query: String) = fire(Dispatchers.IO) {
        val searchPlace = Net.searchPlace(query)
        if (searchPlace.status == "ok") {
            val place = searchPlace.place
            Result.success(place)
        } else {
            Result.failure(RuntimeException(searchPlace.status))
        }
    }

    //异步请求，同步回调
    fun refreshWeather(lng: String, lat: String, placeName: String) = fire(Dispatchers.IO) {
        coroutineScope {
            val realTime = async { Net.getRealTimeWeather(lng, lat) }
            val daily = async { Net.getDailyWeather(lng, lat) }

            val realResponse = realTime.await()
            val dailyResponse = daily.await()

            if (realResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather = Weather(realResponse.result.realTime, dailyResponse.result.daily)
                Result.success(weather)
            } else {
                Result.failure(RuntimeException("real: ${realResponse.status}; daily: ${dailyResponse.status}"))
            }
        }
    }

    fun savePlace(place: Place) = PlaceDao.savePlace(place)

    fun getSavePlace() = PlaceDao.getPlace()

    fun isPlaceSaved() = PlaceDao.isPlaceSaved()

    //统一Try-catch处理
    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }

            emit(result)
        }
}