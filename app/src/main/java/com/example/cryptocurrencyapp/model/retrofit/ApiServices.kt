package com.example.cryptocurrencyapp.model.retrofit

import com.example.cryptocurrencyapp.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("assets")
    fun getTopAssets(@Query("limit") limit:Int): Call<RetrofitData<List<Asset>>>

    @GET("assets/{id}")
    fun getAsset(@Path("id") id:String): Call<RetrofitData<AssetInfo>>

    @GET("assets/{id}/history")
    fun getHistoryAssets(
        @Path("id") id:String,
        @Query("interval") interval:String,
        @Query("start") start:Long,
        @Query("end") end:Long
    ): Call<RetrofitData<List<AssetPriceTime>>>
}
//https://api.coincap.io/v2/assets?limit=10
