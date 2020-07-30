package com.android.hsicen.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.android.hsicen.sunnyweather.logic.Repository

/**
 * 作者：hsicen  2020/7/30 9:22
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：ViewModel层
 */
class PlaceViewModel : ViewModel() {
    private val _searchStr = MutableLiveData<String>()

    val placeData = Transformations.switchMap(_searchStr) { query ->
        Repository.searchPlace(query)
    }

    fun searchPlace(query: String) {
        _searchStr.value = query
    }

}