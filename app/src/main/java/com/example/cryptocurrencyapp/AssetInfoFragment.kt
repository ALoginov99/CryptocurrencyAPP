package com.example.cryptocurrencyapp

import ASSET_ID
import android.graphics.Color
import android.icu.text.CompactDecimalFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.android.synthetic.main.fragment_asset_info.*
import models.AssetHistory
import models.AssetInfoData
import retrofit.AssetsServices
import retrofit.Common
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.Locale


class AssetInfoFragment : Fragment() {

    private var id:String? = null
    private lateinit var mService: AssetsServices

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mService = Common.assetsService
        id = arguments?.getString(ASSET_ID)

        mService.getAsset(id!!).enqueue(object : Callback<AssetInfoData> {
            override fun onFailure(call: Call<AssetInfoData>, t: Throwable) {}

            override fun onResponse(call: Call<AssetInfoData>, response: Response<AssetInfoData>) {
                val asset = (response.body() as AssetInfoData).data

                //property binding
                nameView.text = asset.name
                symbolView.text = asset.symbol
                changePercent24HrView.text = String.format(Locale.US, "%.4f", asset.changePercent24Hr)
                //color changePercent24Hr
                if(asset.changePercent24Hr!! >= 0)
                    changePercent24HrView.setTextColor(Color.parseColor("#00FF00"))
                else
                    changePercent24HrView.setTextColor(Color.parseColor("#FF0000"))


                priceUsdView.text = String.format(Locale.US, "%.4f", asset.priceUsd)

                if(asset.maxSupply != null)
                    maxSupplyView.text = String.format(Locale.US, "%.4f", asset.maxSupply)
                else
                    maxSupplyRow.visibility = View.GONE

                if(asset.vwap24Hr != null)
                    vwap24HrView.text = String.format(Locale.US, "%.4f", asset.vwap24Hr)
                else
                    vwap24HrRow.visibility = View.GONE

                volumeUsd24HrView.text = String.format(Locale.US, "%.4f", asset.volumeUsd24Hr)
                marketCapUsdView.text = String.format(Locale.US, "%.4f", asset.marketCapUsd)
                supplyView.text = String.format(Locale.US, "%.4f", asset.supply)
            }
        })


        //get history and setting graph properties
        mService.getHistoryAssets(
            id!!,
            "d1",
            Calendar.getInstance().timeInMillis - 2592000000,//-30days
            Calendar.getInstance().timeInMillis
        ).enqueue(object : Callback<AssetHistory> {
            override fun onFailure(call: Call<AssetHistory>, t: Throwable) {
                Log.d("message", t.message.toString())
            }

            override fun onResponse(call: Call<AssetHistory>, response: Response<AssetHistory>) {
                val prices = (response.body() as AssetHistory).data
                Log.d("prices", prices.toString())

                val entries = ArrayList<Entry>()
                prices.forEach { p -> entries.add(Entry(p.date.time.toFloat(), p.priceUsd.toFloat())) }

                val vl = LineDataSet(entries, "Assets")

                vl.setDrawValues(false)
                vl.setDrawFilled(true)
                vl.lineWidth = 3f
                vl.fillColor = Color.GRAY
                vl.fillAlpha = Color.RED

                lineChart.xAxis.labelRotationAngle = 0f

                val xAxis = lineChart.xAxis
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.setDrawGridLines(false)

                xAxis.valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        return value.toString()
                    }

                    override fun getAxisLabel(value: Float, axis: AxisBase): String {
                        return SimpleDateFormat("MMM dd", Locale.ENGLISH).format(Date(value.toLong()))
                    }
                }

                lineChart.data = LineData(vl)

                lineChart.axisRight.isEnabled = false
//                lineChart.xAxis.axisMaximum = j+0.1f

                lineChart.setTouchEnabled(true)
                lineChart.setPinchZoom(true)

                lineChart.description.text = "Days"
                lineChart.setNoDataText("No forex yet!")

                lineChart.animateX(1800, Easing.EaseInExpo)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_asset_info, container, false)
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            AssetInfoFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
            }
    }
}