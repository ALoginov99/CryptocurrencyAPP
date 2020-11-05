package com.example.cryptocurrencyapp

import AssetsAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_assets.*
import models.AssetsData
import retrofit.Common
import retrofit.AssetsServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Assets : Fragment() {

    lateinit var mService: AssetsServices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_assets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list_assets_view.apply {
            mService = Common.assetsService
            layoutManager = LinearLayoutManager(activity)

            mService.getTopAssets(15).enqueue(object : Callback<AssetsData> {
                override fun onFailure(call: Call<AssetsData>, t: Throwable) {}

                override fun onResponse(call: Call<AssetsData>, response: Response<AssetsData>) {
                    adapter = AssetsAdapter(( response.body() as AssetsData).data)
                    adapter?.notifyDataSetChanged()
                    list_assets_view.adapter = adapter
                }
            })
        }
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            Assets().apply {

            }
    }
}