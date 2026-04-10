package musicPlayer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Text
import coil.compose.AsyncImage
import com.model.Song

@Composable
fun PlayerScreen(
    viewModel: PlayerViewModel = hiltViewModel()
) {
    val song = remember {
        viewModel.getSong()
    }

    if (song == null)
    {
        Text("Erro: música não encontrada")
        return
    }

    NowPlayingScreen(song = song)
}

@Composable
fun NowPlayingScreen(
    song: Song,
    viewModel: PlayerViewModel = viewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(song.previewUrl) {
        viewModel.initPlayer(context, song.previewUrl)
    }

    val isPlaying = viewModel.isPlaying
    val position = viewModel.currentPosition
    val duration = viewModel.duration

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Now playing",
            color = Color.White,
            fontSize = 16.sp
        )//Text

        Spacer(modifier = Modifier.height(32.dp))

        AsyncImage(
            model = song.highQualityImage,
            contentDescription = null,
            modifier = Modifier
                .size(280.dp)
                .clip(RoundedCornerShape(20.dp))
        )//AsyncImage

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = song.title,
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )//Text

        Text(
            text = song.artist,
            color = Color.Gray,
            fontSize = 16.sp
        )//Text

        Spacer(modifier = Modifier.height(24.dp))

        // 🎚️ Progress bar
        Slider(
            value = position.toFloat(),
            onValueChange = { viewModel.seekTo(it.toLong()) },
            valueRange = 0f..(duration.toFloat().coerceAtLeast(1f)),
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = Color.White
            )
        )//Slider

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(formatTime(position), color = Color.Gray)
            Text(formatTime(duration), color = Color.Gray)
        }//Row

        Spacer(modifier = Modifier.height(24.dp))

        // 🎵 Controls
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            IconButton(onClick = { /* skip back fake */ }) {
                Icon(Icons.Default.SkipPrevious, null, tint = Color.White)
            }

            IconButton(
                onClick = { viewModel.playPause() },
                modifier = Modifier.size(72.dp)
            ) {
                Icon(
                    if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                    null,
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
            }

            IconButton(onClick = { /* skip next fake */ }) {
                Icon(Icons.Default.SkipNext, null, tint = Color.White)
            }
        }//Row
    }//Column
}//Composable

fun formatTime(ms: Long): String {
    val totalSeconds = ms / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%d:%02d".format(minutes, seconds)
}