package songs.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import songs.SongsViewModel

class SongsViewModelFactory(
    private val songsRepository: SongsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SongsViewModel(songsRepository) as T
    }
}