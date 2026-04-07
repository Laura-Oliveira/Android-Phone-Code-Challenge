package com.challenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import com.challenge.navigation.AppNavigation
import kotlinx.coroutines.delay
import com.challenge.ui.theme.SongsAppTheme

class SplashScreen : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {
            SongsAppTheme() {
                AppNavigation()
            }
        }
    }
}

@Composable
fun SplashScreenContent(onNavigateToHome: () -> Unit)
{
    val scale = androidx.compose.animation.core.animateFloatAsState(
        targetValue = 1f,
        animationSpec = androidx.compose.animation.core.tween(1000)
    )

    LaunchedEffect(Unit)
    {
        delay(2500)
        onNavigateToHome()
    }

    Surface {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onBackground),
            contentAlignment = Alignment.Center
        )
        {
            Image(
                painter = painterResource(id = com.playlist.R.mipmap.app_icon_orange_foreground),
                contentDescription = "App Icon",
                modifier = Modifier
                    .fillMaxSize(0.6f)
                    .graphicsLayer(
                        scaleX = scale.value,
                        scaleY = scale.value
                    )
            )//Image
        }//Box
    }//Surface
}