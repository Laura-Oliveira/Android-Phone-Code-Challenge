package songs.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.navigation.NavigatorInterface
import songs.SongsViewModel

@Composable
fun SongsListScreenContent(
    viewModel: SongsViewModel,
    navigator: NavigatorInterface
) {
    val songs = viewModel.getSongs("eletronic").collectAsLazyPagingItems()


  //  val songs = viewModel.getSongs(text.ifBlank { "electronic" }).collectAsLazyPagingItems()

    //  val songs: LazyPagingItems<Song> = viewModel.songs.collectAsLazyPagingItems()

    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        Text(
            text = "Songs",
            color = Color.White,
            fontSize = 28.sp,
            modifier = Modifier.padding(16.dp)
        )//Text

        SearchBar(
            value = text,
            onValueChange = {
                text = it
                //viewModel.onSearchChanged(it)
            }
        )//SearchBar

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(songs.itemCount) { index ->
                songs[index]?.let { song ->
                    SongItem(
                        song = song,
                        onClick = {
                            navigator.onNavigateToPlayer(song)
                        }//onCLick
                    )//SongItem
                }//songs
            }//items
        }//LazyColumn
    }//Column
}//Composable