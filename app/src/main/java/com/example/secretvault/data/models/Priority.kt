package com.example.secretvault.data.models

import androidx.compose.ui.graphics.Color

enum class Priority(val color: Color) {
    HIGH(color = Color.Red),
    MEDIUM(color = Color.Yellow),
    LOW(color = Color.Green),
    NONE(color = Color.Transparent)
}