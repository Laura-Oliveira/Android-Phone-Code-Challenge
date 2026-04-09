package com.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import com.core.navigation.AppNavigation
import kotlinx.coroutines.delay
import com.core.ui.theme.SongsAppTheme
import com.playlist.R

class SplashScreen : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {
            SongsAppTheme() {
                AppNavigation()
            }//Theme
        }//Content
    }
}

@Composable
fun SplashScreenContent(onNavigateToHome: () -> Unit)
{
    val scale = animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(1000)
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
                painter = painterResource(id = R.mipmap.app_icon_orange_foreground),
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
}//Composable