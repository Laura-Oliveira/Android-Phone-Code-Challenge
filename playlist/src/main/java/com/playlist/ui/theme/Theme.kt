package com.playlist.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// 🔵 Base (Moises-style)
val BluePrimary = Color(0xFF286CFF)
val BlueDark = Color(0xFF010413)

// 🔶 Accent
val OrangeAccent = Color(0xFFFF7A00)
val OrangeSoft = Color(0xFFFF9F1C)

// 🌑 Backgrounds
val Background = Color(0xFF05070D)
val Surface = Color(0xFF111827)
val SurfaceVariant = Color(0xFF1A2238)
val NavyBlue = Color(0xFF111827)

// ⚪ Text
val OnPrimary = Color.White
val OnBackground = Color(0xFFFFFFFF)
val OnSurface = Color(0xFFC9D1E3)

private val DarkColorScheme = darkColorScheme(
    primary = BluePrimary,
    secondary = OrangeAccent,
    background = Background,
    surface = Surface,
    onPrimary = OnPrimary,
    onBackground = OnBackground,
    onSurface = OnSurface
)

private val LightColorScheme = lightColorScheme(

    // 🔵 BRAND
    primary = BluePrimary,
    onPrimary = Color.White,

    // 🔶 ACCENT
    secondary = OrangeAccent,
    onSecondary = Color.White,

    // 🌑 BACKGROUND (off-white)
    background = Color(0xFFF7F9FC),
    onBackground =  Color(0xFF111827),

    // 🧾 SURFACE
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1C1C1E),

    // 🪟 VARIANT (cards / containers)
    surfaceVariant = Color(0xFFE9EEF6),
    onSurfaceVariant = Color(0xFF5C6B7A),

    // ✨ EXTRA (diferencial)
    tertiary = OrangeSoft,
    onTertiary = Color.Black

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