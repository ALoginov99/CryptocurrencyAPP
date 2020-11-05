package retrofit

import models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AssetsServices {
    @GET("assets")
    fun getTopAssets(@Query("limit") limit:Int): Call<AssetsData>

    @GET("assets/{id}")
    fun getAsset(@Path("id") id:String): Call<AssetInfoData>

    @GET("assets/{id}/history")
    fun getHistoryAssets(
        @Path("id") id:String,
        @Query("interval") interval:String,
        @Query("start") start:Long,
        @Query("end") end:Long
    ): Call<AssetHistory>
}
//https://api.coincap.io/v2/assets?limit=10
