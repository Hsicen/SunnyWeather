package com.android.hsicen.sunnyweather.ui.weather

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.hsicen.sunnyweather.R

/**
 * 作者：hsicen  2020/7/31 8:51
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：天气详情页
 */
class WeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
    }


    companion object {
        private const val LNG = "location_lng"
        private const val LAT = "location_lat"
        private const val NAME = "location_name"

        fun start(ctx: Context, lng: String, lat: String, placeName: String) {
            val intent = Intent(ctx, WeatherActivity::class.java).apply {
                putExtra(LNG, lng)
                putExtra(LAT, lat)
                putExtra(NAME, placeName)
            }

            ctx.startActivity(intent)
        }
    }
}