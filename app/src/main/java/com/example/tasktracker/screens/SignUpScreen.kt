package com.example.tasktracker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tasktracker.R
import com.example.tasktracker.components.components.HeadingTextComponent
import com.example.tasktracker.components.components.TextLink
import com.example.tasktracker.components.fragments.SignUpFragmentManager
import com.example.tasktracker.ui.theme.colorBackground

@Composable
fun SignUpScreen() {
    Surface(
        modifier = Modifier
            .padding(start = 38.dp, end = 38.dp, top = 75.dp, bottom = 40.dp)
    ) {
        Column(
            modifier = Modifier
                .background(colorBackground)
        ) {
            HeadingTextComponent(value = stringResource(id = R.string.header_text_registration))
            SignUpFragmentManager()
            Spacer(modifier = Modifier.height(40.dp))
            TextLink(stringResource(id = R.string.text_account))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewOfSignUpScreen() {
    SignUpScreen()
}