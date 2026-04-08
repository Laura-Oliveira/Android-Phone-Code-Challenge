package songsPlaylist.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.room.Room
import coil.compose.AsyncImage
import com.playlist.ui.theme.SongsAppTheme
import data.local.AppDatabase
import data.local.SearchSongsUseCase
import data.model.Song
import data.remote.ItunesAPI
import data.remote.ItunesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import songsPlaylist.SongsRepositoryImpl
import songsPlaylist.SongsViewModel
import songsPlaylist.SongsViewModelFactory

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

    val repository = SongsRepositoryImpl(api)

    val useCase = SearchSongsUseCase(repository)

    val viewModel: SongsViewModel = viewModel(
        factory = SongsViewModelFactory(useCase)
    )

    SongsListScreenContent(viewModel)
}

//
//@Composable
//fun SongsScreen()
//{
//    val context = LocalContext.current
//
//    val db = Room.databaseBuilder(
//        context,
//        AppDatabase::class.java,
//        "app_database"
//    ).build()
//
//    val api = Retrofit.Builder()
//        .baseUrl("https://itunes.apple.com/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//        .create(ItunesAPI::class.java)
//
//    val repository = SongsRepositoryImpl(api, db)
//
//    val useCase = SearchSongsUseCase(repository)
//    val viewModel: SongsViewModel = viewModel(
//        factory = SongsViewModelFactory(useCase)
//    )
//
//    SongsListScreenContent(viewModel)
//}

//@Composable
//fun SongsListScreenContent(viewModel: SongsViewModel) {
//
//    val songs = viewModel.songs.collectAsLazyPagingItems()
////    val showLocal = viewModel.showLocal.collectAsState().value
////    val recentSongs = viewModel.recentSongs.collectAsState().value
//
//    var text by remember { mutableStateOf("") }
//
//
//
//
//    Column {
////        TextField(
////            value = text,
////            onValueChange = {
////                text = it
////                viewModel.onSearchChanged(it)
////            }
////        )
//
//        Column {
//
//            LazyColumn {
//                items(songs.itemCount) { index ->
//                    songs[index]?.let {
//                        SongsItem(
//                            song = it,
//                            onClick = {}
//                        )
//                    }
//                }
//            }
//        }
//    }
//}

@Composable
fun SongsListScreenContent(viewModel: SongsViewModel) {

    val songs: LazyPagingItems<Song> = viewModel.songs.collectAsLazyPagingItems()
  //  val songs by viewModel.songs.collectAsState()

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
        )

        SearchBar(
            value = text,
            onValueChange = {
                text = it
                viewModel.onSearchChanged(it)
            }
        )

    Spacer(modifier = Modifier.height(8.dp))

//        TextField(
//            value = text,
//            onValueChange = {
//                text = it
//                viewModel.onSearchChanged(it)
//            },
//            placeholder = { Text("Search") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            shape = RoundedCornerShape(24.dp)
//        )

        LazyColumn {
            items(songs.itemCount) { index ->
                songs[index]?.let {
                    SongItem(it)
                }
            }
        }
    }
}

@Composable
fun SongItem(song: Song)
{

    val highQualityImage = song.artwork.replace("100x100", "300x300")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = highQualityImage,
            contentDescription = null,
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = song.title,
                color = Color.White,
                fontSize = 16.sp
            )
            Text(
                text = song.artist,
                color = Color.Gray,
                fontSize = 14.sp
            )
        }

        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = null,
            tint = Color.Gray
        )
    }
}

@Composable
fun SearchBar(value: String, onValueChange: (String) -> Unit) {

    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text("Search") },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = null)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(13.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFF2A2A2A),
            unfocusedContainerColor = Color(0xFF2A2A2A),
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedIndicatorColor = Color(0xFFFF7A00),
            unfocusedIndicatorColor = Color(0xFFFF7A00),        // opcional
        )
    )
}

//        LazyColumn {
//
//            if (showLocal) {
//                items(recentSongs.size) { index ->
//                    SongsItem(
//                        song = recentSongs[index],
//                        onClick = {}
//                    )
//                }
//            } else {
//                items(songs.itemCount) { index ->
//                    songs[index]?.let {
//                        SongsItem(
//                            song = it,
//                            onClick = {}
//                        )
//                    }
//                }
//            }
//        }
//    }
//}

//@Composable
//fun SongsListScreenContent(viewModel: SongsViewModel) {
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
//                    SongsItem(
//                        it,
//                        onClick = TODO()
//                    )
//                }
//            }
//        }
//    }
//}

//@Composable
//fun SongsItem(
//    song: Song,
//    onClick: () -> Unit
//) {
//    Column(modifier = Modifier.clickable { onClick() }) {
//        Text(text = song.title)
//        Text(text = song.artist)
//    }
//}

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