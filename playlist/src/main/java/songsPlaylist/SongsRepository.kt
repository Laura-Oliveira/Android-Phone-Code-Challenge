package songsPlaylist

import kotlinx.coroutines.flow.Flow
import androidx.paging.PagingData
import data.model.Song

interface SongsRepository {
    fun searchSongs(query: String): Flow<PagingData<Song>>
    suspend fun getRecentSongs(): List<Song>
    suspend fun hasLocalData(): Boolean
}

