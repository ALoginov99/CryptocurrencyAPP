package com.example.cryptocurrencyapp.view.adapter.viewHolders

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyapp.model.Asset
import com.example.cryptocurrencyapp.viewModel.AssetListViewModel
import com.example.cryptocurrencyapp.BR

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

//        itemView.onClick {
//            val bundle = bundleOf("url" to itemData.html_url)
//            itemView.findNavController().navigate(R.id.action_repoListFragment_to_repoDetailFragment, bundle)
//        }
    }
}