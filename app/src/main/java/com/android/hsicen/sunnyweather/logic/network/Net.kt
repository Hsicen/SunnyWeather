package com.android.hsicen.sunnyweather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * 作者：hsicen  2020/7/30 9:01
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：统一网络请求封装
 */
object Net {
    private val placeService = ServiceCreator.create(PlaceService::class.java)

    //发起网络请求
    suspend fun searchPlace(query: String) = placeService.searchPlace(query).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}