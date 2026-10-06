package com.example.fixit

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

// Single source of truth. Ideally wire this into your Typography in Type.kt
// so you don't need to pass fontFamily on every Text.
val ConseFontFamily = FontFamily(
    Font(R.font.consebold, FontWeight.Bold)
)
