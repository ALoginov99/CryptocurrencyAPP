package com.example.cryptocurrencyapp.view.adapter

import android.graphics.Color
import androidx.databinding.BindingAdapter
import com.example.cryptocurrencyapp.model.AssetPriceTime
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@BindingAdapter("android:setData")
fun setData(lineChart: LineChart, data: List<AssetPriceTime>?) {
    if (data == null) return

    val entries = ArrayList<Entry>()
    data.forEach { p -> entries.add(Entry(p.date.time.toFloat(), p.priceUsd.toFloat())) }

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
//  lineChart.xAxis.axisMaximum = j+0.1f

    lineChart.setTouchEnabled(true)
    lineChart.setPinchZoom(true)

    lineChart.description.text = "Days"
    lineChart.setNoDataText("No forex yet!")

    lineChart.animateX(1800, Easing.EaseInExpo)
}