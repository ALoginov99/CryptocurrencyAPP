package com.example.cryptocurrencyapp.view.adapter.viewHolders

import ASSET_ID
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyapp.model.Asset
import com.example.cryptocurrencyapp.viewModel.AssetListViewModel
import com.example.cryptocurrencyapp.BR
import com.example.cryptocurrencyapp.R

class AssetListViewHolder constructor(
    private val dataBinding: ViewDataBinding,
    private val repoListViewModel: AssetListViewModel
)
    : RecyclerView.ViewHolder(dataBinding.root) {

//    val avatarImage = itemView.item_avatar
    fun setup(itemData: Asset) {
        dataBinding.setVariable(BR.assetData, itemData)
        dataBinding.executePendingBindings()

//        Picasso.get().load(itemData.owner.avatar_url).into(avatarImage);

        itemView.setOnClickListener {view->
            val bundle = bundleOf(ASSET_ID to itemData.id)
            itemView.findNavController().navigate(R.id.action_assets_to_assetInfoFragment,bundle)
        }
    }
}