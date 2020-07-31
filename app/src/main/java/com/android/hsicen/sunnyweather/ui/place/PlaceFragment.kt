package com.android.hsicen.sunnyweather.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.hsicen.sunnyweather.MainActivity
import com.android.hsicen.sunnyweather.R
import com.android.hsicen.sunnyweather.ui.weather.WeatherActivity
import kotlinx.android.synthetic.main.layout_fragment_place.*

/**
 * 作者：hsicen  2020/7/30 22:33
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：SunnyWeather
 */
class PlaceFragment : Fragment() {
    private val mViewModel by lazy {
        ViewModelProvider(this)[PlaceViewModel::class.java]
    }

    private val mAdapter by lazy {
        PlaceAdapter(this, mViewModel.placeList)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_fragment_place, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity is MainActivity && mViewModel.isPlaceSaved()) {
            val place = mViewModel.getSavePlace()
            //WeatherActivity.start(requireActivity())
        }


        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = mAdapter

        searchPlaceEdit.addTextChangedListener { editable ->
            val content = editable?.toString() ?: ""

            if (content.isNotEmpty()) {
                mViewModel.searchPlace(content)
            } else {
                recyclerView.visibility = View.GONE
                bgImageView.visibility = View.VISIBLE
                mViewModel.placeList.clear()
                mAdapter.notifyDataSetChanged()
            }
        }

        mViewModel.placeData.observe(viewLifecycleOwner, Observer { result ->
            val places = result.getOrNull()
            if (places.isNullOrEmpty()) {
                result.exceptionOrNull()?.printStackTrace()
                Toast.makeText(activity, "未能查询到任何地点", Toast.LENGTH_SHORT).show()
            } else {
                recyclerView.visibility = View.VISIBLE
                bgImageView.visibility = View.GONE
                mViewModel.placeList.clear()
                mViewModel.placeList.addAll(places)
                mAdapter.notifyDataSetChanged()
            }
        })
    }
}