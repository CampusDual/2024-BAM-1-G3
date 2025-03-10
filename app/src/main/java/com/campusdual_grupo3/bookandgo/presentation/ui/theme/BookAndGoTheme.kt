package com.campusdual_grupo3.bookandgo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Paleta de colores personalizada
private val LightColorPalette = lightColorScheme(
    primary = Color(0xFF6200EE),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD7DAFF),
    onPrimaryContainer = Color(0xFF1C1B1F),
    secondary = Color(0xFF64748B),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE7E0EC),
    onSecondaryContainer = Color(0xFF1C1B1F),
    tertiary = Color(0xFF006C44),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFF9CEFD5),
    onTertiaryContainer = Color(0xFF1C1B1F),
    background = Color(0xFFFDFDFD),
    onBackground = Color(0xFF1C1B1F),
    surface = Color(0xFFFDFDFD),
    onSurface = Color(0xFF1C1B1F),
    error = Color(0xFFB00020),
    onError = Color.White,
    errorContainer = Color(0xFFFCD8DC),
    onErrorContainer = Color(0xFF690005),
    outline = Color(0xFF73777F)
)

private val DarkColorPalette = darkColorScheme(
    primary = Color(0xFFD7DAFF),
    onPrimary = Color(0xFF350062),
    primaryContainer = Color(0xFF4C2D8A),
    onPrimaryContainer = Color(0xFFD7DAFF),
    secondary = Color(0xFFC4C7D0),
    onSecondary = Color(0xFF313338),
    secondaryContainer = Color(0xFF474A52),
    onSecondaryContainer = Color(0xFFE2E3EB),
    tertiary = Color(0xFF9CEFD5),
    onTertiary = Color(0xFF003B24),
    tertiaryContainer = Color(0xFF005235),
    onTertiaryContainer = Color(0xFF9CEFD5),
    background = Color(0xFF1C1B1F),
    onBackground = Color(0xFFE5E1E6),
    surface = Color(0xFF1C1B1F),
    onSurface = Color(0xFFE5E1E6),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFB4AB),
    outline = Color(0xFF8D9199)
)

// Tipografía personalizada
private val AppTypography = Typography(
    displayLarge = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = 0.sp
    ),
    displayMedium = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),
    headlineLarge = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)

// Función principal del tema
@Composable
fun BookAndGoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = Shapes(
            small = RoundedCornerShape(4.dp),
            medium = RoundedCornerShape(8.dp),
            large = RoundedCornerShape(16.dp)
        ),
        content = content
    )
}