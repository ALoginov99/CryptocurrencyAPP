package com.example.cryptocurrencyapp.model

import java.sql.Date

data class Asset(
    val id:String? = null,
    val name:String? = null,
    val rank:Int? = null,
    val symbol:String? = null,
    val changePercent24Hr:Double? = null,
    val priceUsd:Double? = null
)

data class AssetInfo(
    val id:String? = null,
    val name:String? = null,
    val rank:Int? = null,
    val symbol:String? = null,
    val changePercent24Hr:Double? = null,
    val volumeUsd24Hr:Double? = null,
    val vwap24Hr:Double? = null,
    val priceUsd:Double? = null,
    val marketCapUsd:Double? = null,
    val supply:Double? = null,
    val maxSupply:Double? = null

)
data class AssetPriceTime (val priceUsd:Double, val time:Long , val date:Date)

data class RetrofitData<T> (val data: T)