package album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import data.model.Song
import kotlinx.coroutines.flow.Flow
import songs.repository.SongsRepository
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val repository: SongsRepository
) : ViewModel() {

    fun getSongs(query: String): Flow<PagingData<Song>> {
        return repository.searchSongs(query)
            .cachedIn(viewModelScope)
    }
}