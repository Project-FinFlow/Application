package com.example.finflow.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ColorDarkSurface = Color(0xFF1C2938)

private val DarkColorScheme = darkColorScheme(
    primary = FinTealSoft,
    secondary = FinBlueSoft,
    tertiary = FinYellow,
    background = FinInk,
    surface = ColorDarkSurface,
    onPrimary = FinInk,
    onSecondary = FinInk,
    onTertiary = FinInk,
    onBackground = FinSurface,
    onSurface = FinSurface
)

private val LightColorScheme = lightColorScheme(
    primary = FinTeal,
    secondary = FinBlue,
    tertiary = FinYellow,
    background = FinBackground,
    surface = FinSurface,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = FinInk,
    onBackground = FinInk,
    onSurface = FinInk,
    outline = FinBorder
)

@Composable
fun FinFLowTheme(
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
