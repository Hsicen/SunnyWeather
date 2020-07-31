package com.android.hsicen.sunnyweather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.android.hsicen.sunnyweather.logic.Repository
import com.android.hsicen.sunnyweather.logic.model.Location

/**
 * 作者：hsicen  2020/7/31 8:52
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：天气详情页
 */
class WeatherViewModel : ViewModel() {

    private val _location = MutableLiveData<Location>()

    var locationLng = ""
    var locationLat = ""
    var placeName = ""

    val weatherLiveData = Transformations.switchMap(_location) { location ->
        Repository.refreshWeather(location.lng, location.lat, placeName)
    }

    fun refreshWeather(lng: String, lat: String) {
        _location.value = Location(lng, lat)
    }
}
