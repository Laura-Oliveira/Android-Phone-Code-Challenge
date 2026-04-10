package musicPlayer.ui

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.wear.compose.material.Text
import com.model.Song
import musicPlayer.PlayerViewModel

@Composable
fun PlayerScreen(
    song:Song?,
    viewModel: PlayerViewModel = hiltViewModel(),
    onBack:() -> Unit
) {
    if (song == null)
    {
        Text("Erro: música não encontrada")
        return
    }
    NowPlayingScreen(song = song, viewModel = viewModel, onBack = onBack)
}