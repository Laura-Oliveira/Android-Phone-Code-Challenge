package songsPlaylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import data.local.SearchSongsUseCase

class SongsViewModelFactory(
    private val searchSongsUseCase: SearchSongsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SongsViewModel(searchSongsUseCase) as T
    }
}