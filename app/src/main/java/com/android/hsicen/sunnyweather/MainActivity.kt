package com.android.hsicen.sunnyweather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.hsicen.sunnyweather.ui.place.PlaceViewModel

class MainActivity : AppCompatActivity() {
    private val placeViewModel by lazy {
        ViewModelProvider(this).get(PlaceViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initObserver()
    }

    private fun initObserver() {
    }
}