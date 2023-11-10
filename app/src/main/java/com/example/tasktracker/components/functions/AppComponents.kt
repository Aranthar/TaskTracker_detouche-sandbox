package com.example.tasktracker.components.functions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import com.example.tasktracker.R
import com.example.tasktracker.components.componentsShape
import com.example.tasktracker.ui.theme.colorBackground
import com.example.tasktracker.ui.theme.colorPrimary

@Composable
fun HeadingTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn()
            .background(MaterialTheme.colorScheme.background),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSecondary
    )
}

@Composable
fun NormalTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn()
            .background(MaterialTheme.colorScheme.background),
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSecondary
    )
}

@Composable
fun labelTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn()
            .background(MaterialTheme.colorScheme.background),
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.error
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFiledComponent(labelValue: String, painterResource: Painter) {
    var textValue by rememberSaveable { mutableStateOf("") }
    var isError by rememberSaveable { mutableStateOf(true) }
    val errorMessage = "" // TODO: Перенести текст ошибки

    OutlinedTextField(
        value = textValue,
        onValueChange = {
            textValue = it
            isError = getInfoAboutDefineFieldType(labelValue)
                        },
        label = { Text(text = labelValue) },
        isError = isError,
        supportingText = {
            if (isError) labelTextComponent(errorMessage)
        },
        trailingIcon = {
            if (isError)
                Icon(painter = painterResource(id = R.drawable.error_icon),"error", tint = MaterialTheme.colorScheme.onError)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorPrimary,
            focusedLabelColor = colorPrimary,
            unfocusedBorderColor = Color.LightGray,
            cursorColor = colorPrimary,
            containerColor = colorBackground
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        keyboardActions = KeyboardActions { isError = getInfoAboutDefineFieldType(labelValue) },
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentsShape.small)
    )
}