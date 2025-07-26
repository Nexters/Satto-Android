package com.hanbang.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity

private val DarkColorScheme = darkColorScheme(
    primary = Primary2,
    secondary = Primary2,
    tertiary = Primary2
)

private val LightColorScheme = lightColorScheme(
    primary = Primary2,
    secondary = Primary2,
    tertiary = Primary2
)

@Composable
fun SattoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val density = LocalDensity.current

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val sattoTypography = remember(density) { Typography(density) }

    CompositionLocalProvider(LocalSattoTypography provides sattoTypography) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}

object SattoTheme {
    val typography: SattoTypography
        @Composable
        get() = LocalSattoTypography.current
}
