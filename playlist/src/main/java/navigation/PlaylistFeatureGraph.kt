package navigation

import album.AlbumScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import musicPlayer.ui.PlayerScreen
import songs.ui.SongsScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.model.Song
import com.navigation.FeatureGraph
import com.navigation.NavigatorInterface
import com.navigation.Routes
import songs.SongsViewModel

class PlaylistFeatureGraph : FeatureGraph
{
    @RequiresApi(Build.VERSION_CODES.O)
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navigatorInterface: NavigatorInterface,
        navController: NavController
    ) {
        navGraphBuilder.composable(Routes.HOME.route) {

            val viewModel: SongsViewModel = hiltViewModel()

            SongsScreen(
                navigator = navigatorInterface,
                viewModel = viewModel
            )
        }

        navGraphBuilder.composable(Routes.PLAYER.route) {
            val song: Song? = navController
                .previousBackStackEntry
                ?.savedStateHandle
                ?.get<Song>("song")

            PlayerScreen(song = song,
                onBack = { navController.popBackStack() })
        }

        navGraphBuilder.composable(Routes.ALBUM.route) {
            AlbumScreen()
        }
    }
}