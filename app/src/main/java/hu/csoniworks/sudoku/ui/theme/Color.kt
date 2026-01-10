package hu.csoniworks.sudoku.ui.theme

import androidx.compose.ui.graphics.Color

// ─────────────────────────────
// Core Whites & Neutrals
// ─────────────────────────────
val SterileWhite = Color(0xFFF6F8FA)
val ColdWhite = Color(0xFFE9EEF3)
val PanelWhite = Color(0xFFDCE5EF)

val InkBlueBlack = Color(0xFF1E2A36)
val DeepBlueBlack = Color(0xFF0D1620)

// ─────────────────────────────
// Keyboard Blues (primary identity)
// ─────────────────────────────
val LumonBluePrimary = Color(0xFF4A6FA5)   // main keyboard blue
val LumonBlueSoft = Color(0xFF6F8FB8)      // secondary keys
val LumonBluePale = Color(0xFF9BB8DD)      // highlights / focus
val LumonBlueDim = Color(0xFF3A4F68)       // grid lines / borders

// ─────────────────────────────
// Office Greens (supporting accents)
// ─────────────────────────────
val LumonGreenPrimary = Color(0xFF4F6F64)  // walls / accents
val LumonGreenSoft = Color(0xFF6F8F85)     // secondary surfaces
val LumonGreenPale = Color(0xFF9FB8AF)     // selection / subtle fill
val LumonGreenDim = Color(0xFF3E5650)      // outlines / separators

// ─────────────────────────────
// Dark Surfaces (innie environment)
// ─────────────────────────────
val CorridorBlueDark = Color(0xFF162230)
val CorridorBlueDarker = Color(0xFF1F2F42)
val CorridorBlueDeep = Color(0xFF0D1620)

// ─────────────────────────────
// Feedback & States (muted only)
// ─────────────────────────────
val ErrorMutedRed = Color(0xFF8B4A4A)
val WarningMutedAmber = Color(0xFF8A7A4F)
val SuccessMutedGreen = Color(0xFF5F7F6A)

// ─────────────────────────────
// Sudoku-specific helpers
// ─────────────────────────────
val FixedCellText = InkBlueBlack
val UserInputBlue = LumonBluePrimary
val NoteTextMuted = Color(0xFF6C7A88)
val SelectedCellFill = PanelWhite
val GridLineColor = LumonBlueDim
