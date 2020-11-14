package com.example.cryptocurrencyapp.model.retrofit

import com.example.cryptocurrencyapp.model.AssetsData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoRepository {

    // GET assets list
    fun getAssetList(onResult: (isSuccess: Boolean, response: AssetsData?) -> Unit) {

        Common.assetsService.getTopAssets(15).enqueue(object : Callback<AssetsData> {
            override fun onResponse(call: Call<AssetsData>?, response: Response<AssetsData>?) {
                if (response != null && response.isSuccessful)
                    onResult(true, response.body()!!)
                else
                    onResult(false, null)
            }

            override fun onFailure(call: Call<AssetsData>?, t: Throwable?) {
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