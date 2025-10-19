package com.example.imaan.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = BluePrimary,
    secondary = BlueAccent,
    background = BlueLight,
    surface = CardBackground,
    onPrimary = Color.White,
    onSecondary = BlueDark,
    onBackground = BlueDark,
    onSurface = BlueDark
)

private val DarkColorScheme = darkColorScheme(
    primary = BlueDark,
    secondary = BlueMedium,
    background = Color(0xFF0A192F),
    surface = Color(0xFF112240),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = BlueLight,
    onSurface = BlueLight
)

@Composable
fun IMAANTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}