package songsPlaylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import data.local.SearchSongsUseCase
import data.model.Song
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongsViewModel @Inject constructor(
    private val searchSongs: SearchSongsUseCase
) : ViewModel() {

   // private val query = MutableStateFlow("")
   private val query = MutableStateFlow("electronic")

    val songs = query
        .debounce(500)
        .flatMapLatest { searchSongs(it) }
        .cachedIn(viewModelScope)

    fun onSearchChanged(text: String) {
        query.value = text
    }
}


//
//class SongsViewModel(
//    private val useCase: SearchSongsUseCase
//) : ViewModel() {
//
//    private val query = MutableStateFlow("")
//
//    private val _showLocal = MutableStateFlow(false)
//    val showLocal = _showLocal
//
//    val songs = query
//        .debounce(500)
//        .flatMapLatest { useCase(it) }
//        .cachedIn(viewModelScope)
//
//    private val _recentSongs = MutableStateFlow<List<Song>>(emptyList())
//    val recentSongs = _recentSongs
//
//    init {
//        checkLocalData()
//    }
//
//    private fun checkLocalData() {
//        viewModelScope.launch {
//            val hasData = useCase.hasLocalData()
//
//            if (hasData) {
//                _showLocal.value = true
//                _recentSongs.value = useCase.getRecentSongs()
//            }
//        }
//    }
//
//    fun onSearchChanged(text: String) {
//        _showLocal.value = false // quando digita, volta pro online
//        query.value = text
//    }
//}
