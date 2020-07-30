package com.android.hsicen.sunnyweather.logic

import androidx.lifecycle.liveData
import com.android.hsicen.sunnyweather.logic.network.Net
import kotlinx.coroutines.Dispatchers
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

    //统一Try-catch处理
    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }

            emit(result)
        }
}