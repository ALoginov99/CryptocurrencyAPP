package retrofit

//https://api.coincap.io/v2/assets?limit=10

object Common {
    private const val BASE_URL = "https://api.coincap.io/v2/"
    val assetsService: AssetsServices
        get() = RetrofitClient.getClient(BASE_URL).create(AssetsServices::class.java)
}