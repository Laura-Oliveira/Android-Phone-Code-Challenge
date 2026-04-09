package songs.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import data.remote.ItunesAPI
import kotlinx.coroutines.flow.Flow
import data.remote.SongsPagingSource
import data.model.Song

class SongsRepositoryImpl(
    private val api: ItunesAPI
) : SongsRepository {

    override fun searchSongs(query: String): Flow<PagingData<Song>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false),
            pagingSourceFactory = {
                SongsPagingSource(api, query)
            }
        ).flow
    }

    override suspend fun getRecentSongs(): List<Song> {
        TODO("Not yet implemented")
    }

    override suspend fun hasLocalData(): Boolean {
        TODO("Not yet implemented")
    }
}

//class SongsRepositoryImpl(
//    private val api: ItunesAPI,
//    private val db: AppDatabase
//) : SongsRepository {
//
//    override fun searchSongs(query: String): Flow<PagingData<Song>> {
//        return Pager(
//            config = PagingConfig(pageSize = 20),
//            pagingSourceFactory = {
//                SongsPagingSource(api, db.songDao(), query)
//            }
//        ).flow
//    }
//
//    override suspend fun getRecentSongs(): List<Song> {
//        return db.songDao().getRecentSongs().map { it.toDomain() }
//    }
//
//    override suspend fun hasLocalData(): Boolean {
//        return db.songDao().getRecentSongs().isNotEmpty()
//    }
//}