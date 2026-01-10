package hu.csoniworks.sudoku.ui.theme

import android.app.Activity
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

private val SeveranceLightColorScheme = lightColorScheme(
    primary = Color(0xFF4A6FA5),        // Lumon blue (keyboard accents)
    onPrimary = Color(0xFFFFFFFF),

    secondary = Color(0xFF6F8FB8),      // soft institutional blue
    onSecondary = Color(0xFFFFFFFF),

    background = Color(0xFFF6F8FA),     // sterile white
    onBackground = Color(0xFF1E2A36),   // cold ink

    surface = Color(0xFFE9EEF3),        // office panel white
    onSurface = Color(0xFF1E2A36),

    surfaceVariant = Color(0xFFDCE5EF), // selected cell / subtle contrast
    onSurfaceVariant = Color(0xFF2B3A4A),

    outline = Color(0xFF9FB3C8),        // grid lines

    error = Color(0xFF8B4A4A),
    onError = Color(0xFFFFFFFF)
)

val SeveranceDarkColorScheme = darkColorScheme(
    primary = Color(0xFF7FA6D6),        // illuminated blue keys
    onPrimary = Color(0xFF0D1620),

    secondary = Color(0xFF9BB8DD),
    onSecondary = Color(0xFF0D1620),

    background = Color(0xFF0D1620),     // dark blue office halls
    onBackground = Color(0xFFE6EDF4),

    surface = Color(0xFF162230),        // desk / wall panels
    onSurface = Color(0xFFE6EDF4),

    surfaceVariant = Color(0xFF1F2F42), // focused cell
    onSurfaceVariant = Color(0xFFC7D6E6),

    outline = Color(0xFF3A4F68),        // grid lines

    error = Color(0xFFB97878),
    onError = Color(0xFF0D1620)
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF6F8F85),
    onPrimary = Color(0xFF0F1A1C),

    secondary = Color(0xFF7FA3A2),
    onSecondary = Color(0xFF0F1A1C),

    background = Color(0xFF0F1A1C),
    onBackground = Color(0xFFE6E8EA),

    surface = Color(0xFF1A2629),
    onSurface = Color(0xFFE6E8EA),

    surfaceVariant = Color(0xFF243235),
    onSurfaceVariant = Color(0xFFC8D6D2),

    outline = Color(0xFF3A4D4A),

    error = Color(0xFFB97A7A),
    onError = Color(0xFF0F1A1C)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF4F6F64),
    onPrimary = Color(0xFFF4F6F5),

    secondary = Color(0xFF5E7C7B),
    onSecondary = Color(0xFFF4F6F5),

    background = Color(0xFFF4F6F5),
    onBackground = Color(0xFF1F2A2E),

    surface = Color(0xFFE6E8EA),
    onSurface = Color(0xFF1F2A2E),

    surfaceVariant = Color(0xFFD9E3DF),
    onSurfaceVariant = Color(0xFF1F2A2E),

    outline = Color(0xFF8FA7A0),

    error = Color(0xFF8A4B4B),
    onError = Color(0xFFF4F6F5)
)

@Composable
fun SudokuTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
//        SeveranceDarkColorScheme
        DarkColorScheme
    } else {
//        SeveranceLightColorScheme
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}