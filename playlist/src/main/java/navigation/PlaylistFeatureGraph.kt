package navigation

import album.AlbumScreen
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import musicPlayer.PlayerScreen
import songs.SongsScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.navigation.FeatureGraph
import com.navigation.NavigatorInterface
import com.navigation.Routes
import songs.SongsViewModel

class PlaylistFeatureGraph : FeatureGraph
{
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navigatorInterface: NavigatorInterface
    ) {
        navGraphBuilder.composable(Routes.HOME.route) {

            val viewModel: SongsViewModel = hiltViewModel()

            SongsScreen(
                navigator = navigatorInterface,
                viewModel = viewModel
            )
        }

        navGraphBuilder.composable(Routes.PLAYER.route) {
            PlayerScreen()
        }
        navGraphBuilder.composable(Routes.ALBUM.route) {
            AlbumScreen()
        }
    }
}