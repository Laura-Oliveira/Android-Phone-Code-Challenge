package data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ItunesService
{
    private const val BASE_URL = "https://itunes.apple.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ItunesAPI by lazy {
        retrofit.create(ItunesAPI::class.java)
    }
}