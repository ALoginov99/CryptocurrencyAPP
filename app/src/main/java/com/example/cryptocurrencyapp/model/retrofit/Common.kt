package com.example.cryptocurrencyapp.model.retrofit

//https://api.coincap.io/v2/assets?limit=10

object Common {
    private const val BASE_URL = "https://api.coincap.io/v2/"
    val assetsService: ApiServices
        get() = ApiClient.getClient(BASE_URL).create(ApiServices::class.java)
}