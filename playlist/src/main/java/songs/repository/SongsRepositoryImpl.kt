package songs.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.model.Song
import data.remote.ItunesAPI
import kotlinx.coroutines.flow.Flow
import data.remote.SongsPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongsRepositoryImpl @Inject constructor(
    private val api: ItunesAPI
) : SongsRepository {

    private var selectedSong: Song? = null

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


    override fun getSelectedSong(): Song? = selectedSong

    override fun setSelectedSong(song: Song) {
        selectedSong = song
    }

    override suspend fun getRecentSongs(): List<Song> {
        TODO("Not yet implemented")
    }

    override suspend fun hasLocalData(): Boolean {
        TODO("Not yet implemented")
    }
}