package com.example.tasktracker.components.components

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object ComponentsShape {
    private val ShapeDp = mapOf("large" to 21.dp, "medium" to 8.dp, "small" to 4.dp)

    fun getShapeDp(key: String): Dp {
        return ShapeDp.getOrElse(key) { 0.dp }
    }
}