package com.jamiescode.grazer.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
    darkColorScheme(
        primary = Green, // button
        secondary = RedDark, // bio section
        tertiary = Red,
        background = Black, // screen background
        surface = Black, // top app bar background
        onPrimary = White, // text on primary (button)
        onSecondary = Red,
        onTertiary = Red,
        onBackground = White, // text on background
        outlineVariant = White, // card border
    )

private val LightColorScheme =
    lightColorScheme(
        primary = Green, // button
        secondary = Red, // bio section
        tertiary = Red,
        background = White, // screen background
        surface = White, // top app bar background
        onPrimary = White, // text on primary (button)
        onSecondary = Red,
        onTertiary = Red,
        onBackground = Black, // text on background,
        outlineVariant = Black, // card border
    )

@Composable
fun grazerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme =
        when {
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
        content = content,
    )
}
