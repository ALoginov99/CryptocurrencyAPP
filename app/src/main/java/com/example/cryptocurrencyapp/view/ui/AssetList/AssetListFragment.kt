package com.example.cryptocurrencyapp.view.ui.AssetList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.cryptocurrencyapp.databinding.FragmentAssetListBinding
import com.example.cryptocurrencyapp.view.adapter.AssetListAdapter
import androidx.lifecycle.ViewModelProviders
import com.example.cryptocurrencyapp.viewModel.AssetListViewModel
import kotlinx.android.synthetic.main.fragment_asset_list.*

class AssetListFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentAssetListBinding
    private lateinit var adapter: AssetListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentAssetListBinding.inflate(inflater, container, false).apply {
            viewmodel = ViewModelProviders.of(this@AssetListFragment).get(AssetListViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.viewmodel?.fetchAssetList()

        setupAdapter()
        setupObservers()
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.assetListLive?.observe(viewLifecycleOwner, Observer {
            adapter.updateRepoList(it)
        })

//        viewDataBinding.viewmodel?.toastMessage?.observe(viewLifecycleOwner, Observer {
//            activity?.longToast(it)
//        })
    }

    private fun setupAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            adapter = AssetListAdapter(viewDataBinding.viewmodel!!)
            val layoutManager = LinearLayoutManager(activity)
            asset_list_rv.layoutManager = layoutManager
            asset_list_rv.addItemDecoration(DividerItemDecoration(activity, layoutManager.orientation))
            asset_list_rv.adapter = adapter
        }
    }
}