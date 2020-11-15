package com.example.cryptocurrencyapp.model.retrofit

import com.example.cryptocurrencyapp.model.Asset
import com.example.cryptocurrencyapp.model.AssetInfo
import com.example.cryptocurrencyapp.model.AssetPriceTime
import com.example.cryptocurrencyapp.model.RetrofitData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoRepository {

    fun getAssetHistory(assetId:String,interval:String,start:Long,end:Long,onResult: (isSuccess: Boolean, response: RetrofitData<List<AssetPriceTime>>?) -> Unit) {
        Common.assetsService.getHistoryAssets(assetId,interval,start,end).enqueue(object : Callback<RetrofitData<List<AssetPriceTime>>> {
            override fun onResponse(call: Call<RetrofitData<List<AssetPriceTime>>>?, response: Response<RetrofitData<List<AssetPriceTime>>>?) {
                if (response != null && response.isSuccessful)
                    onResult(true, response.body()!!)
                else
                    onResult(false, null)
            }

            override fun onFailure(call: Call<RetrofitData<List<AssetPriceTime>>>?, t: Throwable?) {
                onResult(false, null)
            }
        })
    }

    fun getAssetInfo(assetId:String,onResult: (isSuccess: Boolean, response: RetrofitData<AssetInfo>?) -> Unit) {
        Common.assetsService.getAsset(assetId).enqueue(object : Callback<RetrofitData<AssetInfo>> {
            override fun onResponse(call: Call<RetrofitData<AssetInfo>>?, response: Response<RetrofitData<AssetInfo>>?) {
                if (response != null && response.isSuccessful)
                    onResult(true, response.body()!!)
                else
                    onResult(false, null)
            }

            override fun onFailure(call: Call<RetrofitData<AssetInfo>>?, t: Throwable?) {
                onResult(false, null)
            }
        })
    }

    // GET assets list
    fun getAssetList(size:Int,onResult: (isSuccess: Boolean, response: RetrofitData<List<Asset>>?) -> Unit) {
        Common.assetsService.getTopAssets(size).enqueue(object : Callback<RetrofitData<List<Asset>>> {
            override fun onResponse(call: Call<RetrofitData<List<Asset>>>?, response: Response<RetrofitData<List<Asset>>>?) {
                if (response != null && response.isSuccessful)
                    onResult(true, response.body()!!)
                else
                    onResult(false, null)
            }

            override fun onFailure(call: Call<RetrofitData<List<Asset>>>?, t: Throwable?) {
                onResult(false, null)
            }
        })
    }

    companion object {
        private var INSTANCE: RepoRepository? = null
        fun getInstance() = INSTANCE
            ?: RepoRepository().also {
                INSTANCE = it
            }
    }
}