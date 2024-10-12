package com.jamiescode.grazer.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

val grazerFont =
    FontFamily(
        Font(R.font.beau, FontWeight.Normal),
    )

private val defaultTypography = Typography()
val Typography =
    Typography(
        displayLarge = defaultTypography.displayLarge.copy(fontFamily = grazerFont),
        displayMedium = defaultTypography.displayMedium.copy(fontFamily = grazerFont),
        displaySmall = defaultTypography.displaySmall.copy(fontFamily = grazerFont),
        headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = grazerFont),
        headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = grazerFont),
        headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = grazerFont),
        titleLarge = defaultTypography.titleLarge.copy(fontFamily = grazerFont),
        titleMedium = defaultTypography.titleMedium.copy(fontFamily = grazerFont),
        titleSmall = defaultTypography.titleSmall.copy(fontFamily = grazerFont),
        bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = grazerFont),
        bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = grazerFont),
        bodySmall = defaultTypography.bodySmall.copy(fontFamily = grazerFont),
        labelLarge = defaultTypography.labelLarge.copy(fontFamily = grazerFont),
        labelMedium = defaultTypography.labelMedium.copy(fontFamily = grazerFont),
        labelSmall = defaultTypography.labelSmall.copy(fontFamily = grazerFont),
    )
