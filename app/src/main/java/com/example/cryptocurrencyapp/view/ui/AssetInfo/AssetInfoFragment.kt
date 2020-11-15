package com.example.cryptocurrencyapp.view.ui.AssetInfo

import ASSET_ID
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.cryptocurrencyapp.databinding.FragmentAssetInfoBinding
import com.example.cryptocurrencyapp.viewModel.AssetInfoViewModel


class AssetInfoFragment : Fragment() {

    private var id: String? = null

    private lateinit var viewDataBinding: FragmentAssetInfoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentAssetInfoBinding.inflate(inflater, container, false).apply {
            viewmodel = ViewModelProviders.of(this@AssetInfoFragment).get(AssetInfoViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id = arguments?.getString(ASSET_ID)

        viewDataBinding.viewmodel?.fetchAssetList(id!!)
    }
}
