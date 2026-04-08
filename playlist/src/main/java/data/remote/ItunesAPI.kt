package data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesApi
{
    @GET("search")
    suspend fun searchSongs(
        @Query("term") term: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 20
    ): ItunesResponse
}