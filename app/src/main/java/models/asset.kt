package models

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

data class AssetsData (val data: List<Asset>)
data class AssetInfoData (val data: AssetInfo)

data class AssetPriceTime (val priceUsd:Double, val time:Long , val date:Date)

data class AssetHistory (val data: List<AssetPriceTime>)

//{"id":"bitcoin",
// "rank":"1",
// "symbol":"BTC",
// "name":"Bitcoin",
// "supply":"18533525.0000000000000000",
// "maxSupply":"21000000.0000000000000000",
// "marketCapUsd":"253688437843.1037512175745000",
// "volumeUsd24Hr":"6869979714.0459845363625018",
// "priceUsd":"13688.0835050592777800",
// "changePercent24Hr":"1.2708879373373492",
// "vwap24Hr":"13774.8916176263459976"}

//{"priceUsd":"3850.3624412349879940","time":1543968000000,"date":"2018-12-05T00:00:00.000Z"}