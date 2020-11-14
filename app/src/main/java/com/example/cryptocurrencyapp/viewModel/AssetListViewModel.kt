package com.example.cryptocurrencyapp.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.cryptocurrencyapp.model.Asset
import com.example.cryptocurrencyapp.model.retrofit.RepoRepository
import com.example.cryptocurrencyapp.view.base.BaseViewModel

class AssetListViewModel : BaseViewModel() {
    val assetListLive = MutableLiveData<List<Asset>>()

    fun fetchRepoList() {
        dataLoading.value = true
        RepoRepository.getInstance().getAssetList() { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess) {
                assetListLive.value = response?.data
                empty.value = false
            } else {
                empty.value = true
            }
        }
    }
}