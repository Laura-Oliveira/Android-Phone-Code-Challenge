package songs.repository

import kotlinx.coroutines.flow.Flow
import androidx.paging.PagingData
import com.model.Song

interface SongsRepository {
    fun searchSongs(query: String): Flow<PagingData<Song>>
    fun getSelectedSong(): Song?
    fun setSelectedSong(song: Song)
    suspend fun getRecentSongs(): List<Song>
    suspend fun hasLocalData(): Boolean
}
