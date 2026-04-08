package songsPlaylist.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.playlist.ui.theme.SongsAppTheme
import data.model.Song
import songsPlaylist.SongsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel



class SongsListScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SongsAppTheme() {
                SongsListScreenContent(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun SongsListScreenContent(viewModel: SongsViewModel) {
    val songs = viewModel.songs.collectAsLazyPagingItems()

    Column {
        TextField(
            value = "",
            onValueChange = viewModel::onSearchChanged
        )

        LazyColumn {
            items(songs.itemCount) { index ->
                songs[index]?.let {
                    SongsItem(it)
                }
            }
        }
    }
}

@Composable
fun SongsItem(
    song: Song,
    onClick: () -> Unit
) {
    Column(modifier = Modifier.clickable { onClick() }) {
        Text(text = song.title)
        Text(text = song.artist)
    }
}

//@Composable
//fun SongsScreen(viewModel: SongsViewModel) {
//    val songs = viewModel.songs.collectAsLazyPagingItems()
//
//    Column {
//        TextField(
//            value = "",
//            onValueChange = viewModel::onSearchChanged
//        )
//
//        LazyColumn {
//            items(songs.itemCount) { index ->
//                songs[index]?.let {
//                    SongItem(it)
//                }
//            }
//        }
//    }
//}