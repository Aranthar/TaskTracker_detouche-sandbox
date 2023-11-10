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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.example.tasktracker.R
import com.example.tasktracker.components.componentsShape
import com.example.tasktracker.ui.theme.colorBackground
import com.example.tasktracker.ui.theme.colorOnError
import com.example.tasktracker.ui.theme.colorOnTertiary
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
fun LabelTextComponent(value: String) {
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
fun EmailFiledComponent(labelValue: String, painterResource: Painter) {
    var textValue by rememberSaveable { mutableStateOf("") }
    var isError by rememberSaveable { mutableStateOf(false) }
    var errorMessage by rememberSaveable { mutableStateOf("Неизвестная ошибка") }
    val emailRegex = Regex("""(\w)+@(\w)+\.(\w)+""")

    OutlinedTextField(
        value = textValue,
        onValueChange = {
            textValue = it
            if (getInfoAboutDatabase(labelValue)) {
                isError = true
                errorMessage = "Данная почта уже существует"
            } else {
                /*
                    If the validation check is successful and the requirements are met,
                    we get the reverse value so as not to show an error when processing is successful
                */
                isError = !textValue.contains(emailRegex)
                if (isError) {
                    errorMessage = "Некорректный домен почты"
                }
            }
        },
        label = {
            Text(
                text = labelValue,
                color = when {
                    !isError && textValue.isNotEmpty() -> colorOnTertiary
                    isError -> colorOnError
                    else -> colorPrimary
                }
            )
        },
        isError = isError,
        supportingText = {
            if (isError) LabelTextComponent(errorMessage)
        },
        trailingIcon = {
            if (isError)
                Icon(painter = painterResource(id = R.drawable.error_icon),"error", tint = MaterialTheme.colorScheme.onError)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = if(!isError && textValue.isNotEmpty()) {
                colorOnTertiary
            } else colorPrimary,
            focusedLabelColor = colorPrimary,
            unfocusedBorderColor = if(!isError && textValue.isNotEmpty()) {
                    colorOnTertiary
                } else Color.LightGray,
            cursorColor = colorPrimary,
            containerColor = colorBackground
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
//        keyboardActions = KeyboardActions { isError = getInfoAboutDatabase(labelValue) },
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentsShape.small)
    )
}