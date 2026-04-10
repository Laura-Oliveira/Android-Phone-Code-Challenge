package songs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.model.Song
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import songs.repository.SongsRepository
import javax.inject.Inject

@HiltViewModel
class SongsViewModel @Inject constructor(
    private val repository: SongsRepository
) : ViewModel() {

    fun getSongs(query: String): Flow<PagingData<Song>> {
        return repository.searchSongs(query)
            .cachedIn(viewModelScope)
    }
}