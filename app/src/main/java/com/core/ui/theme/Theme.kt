package com.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color

// 🌊 TEAL (base)
val TealPrimary = Color(0xFF1F7A7E)
val TealDark = Color(0xFF0B2B2E)
val TealMedium = Color(0xFF3FA3A7)

// 🔥 CORAL (complementar)
val CoralPrimary = Color(0xFFFF6B4A)
val CoralSoft = Color(0xFFFF8A65)

// 🌑 BACKGROUND
val Background = Color(0xFF020606)
val Surface = Color(0xFF0D2325)
val SurfaceVariant = Color(0xFF123033)

// ⚪ TEXT
val OnPrimary = Color.White
val OnBackground = Color(0xFFFFFFFF)
val OnSurface = Color(0xFFCFEAEA)

private val DarkColorScheme = darkColorScheme(
    primary = TealPrimary,
    secondary = CoralPrimary,
    background = Background,
    surface = Surface,
    onPrimary = OnPrimary,
    onBackground = OnBackground,
    onSurface = OnSurface
)

//@Composable
//fun SongsAppTheme(content: @Composable () -> Unit) {
//    MaterialTheme(
//        colorScheme = DarkColorScheme,
//        typography = Typography(),
//        content = content
//    )
//}

//
//private val DarkColorScheme = darkColorScheme(
//    primary = Purple80,
//    secondary = PurpleGrey80,
//    tertiary = Pink80
//)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun SongsAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}