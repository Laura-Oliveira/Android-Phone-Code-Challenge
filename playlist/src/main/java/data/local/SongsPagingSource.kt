package data.local

import androidx.paging.PagingSource
import androidx.paging.PagingState
import data.model.Song
import data.model.SongDao
import data.model.SongEntity
import data.remote.ItunesAPI

class SongsPagingSource(
    private val api: ItunesAPI,
    private val query: String
) : PagingSource<Int, Song>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Song> {
        return try {
            val page = params.key ?: 0

           // val response = api.searchSongs(query, page * 20)
            val response =   api.searchSongs(
                term = "electronic",
                offset = 0
            )

            val songs = response.results.map { it.toDomain() }

            LoadResult.Page(
                data = songs,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (songs.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Song>): Int? = null
}
//
//class SongsPagingSource(
//    private val api: ItunesAPI,
//    private val dao: SongDao,
//    private val query: String
//) : PagingSource<Int, Song>() {
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Song> {
//        return try {
//            val page = params.key ?: 0
//
//            val response = api.searchSongs(query, page * 20)
//
//         //   val songs = response.results.map { it.toDomain() }
//            val songs: List<Song> = response.results.map { it.toDomain() }
//
//            dao.insertSongs(songs.map { it.toEntity() })
//
//            LoadResult.Page(
//                data = songs,
//                prevKey = if (page == 0) null else page - 1,
//                nextKey = if (songs.isEmpty()) null else page + 1
//            )
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, Song>): Int? = null
//
    fun ItunesSongDto.toDomain(): Song {
        return Song(
            id = trackId,
            title = trackName,
            artist = artistName,
            artwork = artworkUrl100
        )
    }

    fun Song.toEntity(): SongEntity {
        return SongEntity(
            id = this.id,
            title = this.title,
            artist = this.artist,
            artwork = this.artwork,
            lastPlayed = System.currentTimeMillis(),
        )
    }
//}