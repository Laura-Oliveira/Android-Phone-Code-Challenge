package musicPlayer

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.model.Song
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import songs.repository.SongsRepository
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val repository: SongsRepository
) : ViewModel() {

    fun getSong(): Song? {
        return repository.getSelectedSong()
    }

    private var player: ExoPlayer? = null

    var isPlaying by mutableStateOf(false)
        private set

    var currentPosition by mutableStateOf(0L)
        private set

    var duration by mutableStateOf(0L)
        private set

    fun initPlayer(context: Context, url: String) {
        player = ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(url))
            prepare()
            playWhenReady = true
        }

        observePlayer()
    }

    private fun observePlayer() {
        viewModelScope.launch {
            while (true) {
                player?.let {
                    currentPosition = it.currentPosition
                    duration = it.duration.coerceAtLeast(0)
                    isPlaying = it.isPlaying
                }
                delay(500)
            }
        }
    }

    fun playPause() {
        player?.let {
            if (it.isPlaying) it.pause() else it.play()
        }
    }

    fun seekTo(position: Long) {
        player?.seekTo(position)
    }

    override fun onCleared() {
        player?.release()
        super.onCleared()
    }
}