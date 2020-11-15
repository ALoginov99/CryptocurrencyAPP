package com.example.cryptocurrencyapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptocurrencyapp.model.AssetInfo
import com.example.cryptocurrencyapp.model.AssetPriceTime
import com.example.cryptocurrencyapp.model.retrofit.RepoRepository
import java.util.*

//class AssetInfoViewModel : BaseViewModel() {
class AssetInfoViewModel: ViewModel() {
    val assetInfo = MutableLiveData<AssetInfo>()
    val assetsHistory = MutableLiveData<List<AssetPriceTime>>()

    fun fetchAssetList(assetId:String) {
        RepoRepository.getInstance().getAssetInfo(assetId) { isSuccess, response ->
            assetInfo.value = response?.data
        }
        RepoRepository.getInstance().getAssetHistory(
            assetId,
            "d1",
            Calendar.getInstance().timeInMillis - 2592000000,//-30days
            Calendar.getInstance().timeInMillis) { isSuccess, response ->
                assetsHistory.value = response?.data
            }
    }
}
