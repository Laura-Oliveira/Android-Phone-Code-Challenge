package songs.ui

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.navigation.NavigatorInterface
import songs.SongsViewModel

@Composable
fun SongsScreen(
    navigator: NavigatorInterface,
    viewModel: SongsViewModel = hiltViewModel()
) {
    SongsListScreenContent(
        viewModel = viewModel,
        navigator = navigator
    )
}