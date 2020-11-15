package com.example.cryptocurrencyapp.view.adapter

import com.example.cryptocurrencyapp.model.Asset
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cryptocurrencyapp.databinding.AssetItemBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyapp.view.adapter.viewHolders.AssetListViewHolder
import com.example.cryptocurrencyapp.viewModel.AssetListViewModel

class AssetListAdapter(private val assetListViewModel: AssetListViewModel)
    : RecyclerView.Adapter<AssetListViewHolder>() {
    var assetList: List<Asset> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = AssetItemBinding.inflate(inflater, parent, false)
        return AssetListViewHolder(dataBinding, assetListViewModel)
    }

    override fun getItemCount() = assetList.size

    override fun onBindViewHolder(holder: AssetListViewHolder, position: Int) {
        holder.setup(assetList[position])
    }

    fun updateRepoList(repoList: List<Asset>) {
        this.assetList = repoList
        notifyDataSetChanged()
    }
}