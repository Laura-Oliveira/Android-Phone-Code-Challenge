package songs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.playlist.ui.theme.SongsAppTheme
import data.remote.ItunesService
import songs.repository.SongsRepositoryImpl
import songs.repository.SongsViewModelFactory
import songs.ui.SongsListScreenContent

class SongsListScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SongsAppTheme() {
                SongsScreen()
            }
        }
    }
}

@Composable
fun SongsScreen() {

    val api = ItunesService.api
    val songsRepository = SongsRepositoryImpl(api)

    val viewModel: SongsViewModel = viewModel(
        factory = SongsViewModelFactory(songsRepository)
    )

    SongsListScreenContent(viewModel)
}