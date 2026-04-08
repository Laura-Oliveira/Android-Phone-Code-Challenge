package data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesAPI
{
//    @GET("search")
//    suspend fun searchSongs(
//        @Query("term") term: String,
//        @Query("offset") offset: Int,
//        @Query("limit") limit: Int = 20
//    ): ItunesResponse

    @GET("search")
    suspend fun searchSongs(
        @Query("term") term: String,
        @Query("media") media: String = "music",
        @Query("entity") entity: String = "song",
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int
    ): ItunesResponse
}