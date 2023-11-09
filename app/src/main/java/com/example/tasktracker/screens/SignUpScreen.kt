package com.example.tasktracker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tasktracker.components.HeadingTextComponent

@Composable
fun SignUpScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        HeadingTextComponent(value = "Регистрация")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewOfSignUpScreen() {
    SignUpScreen()
}