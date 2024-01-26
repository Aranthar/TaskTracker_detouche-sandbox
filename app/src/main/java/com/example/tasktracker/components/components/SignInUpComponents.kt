package com.example.tasktracker.components.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasktracker.R
import com.example.tasktracker.data.ErrorMessageObject.getErrorMessages
import com.example.tasktracker.data.ErrorStatus
import com.example.tasktracker.data.RegexObject
import com.example.tasktracker.ui.theme.colorBackground
import com.example.tasktracker.ui.theme.colorLink
import com.example.tasktracker.ui.theme.colorNotActive
import com.example.tasktracker.ui.theme.colorOnError
import com.example.tasktracker.ui.theme.colorOnSecondary
import com.example.tasktracker.ui.theme.colorOnTertiary
import com.example.tasktracker.ui.theme.fontFamily

// TODO: Отформатировать под проект.
@Composable
fun TextFieldInformationContainer(
    painterResource: Painter,
    placeholder: String
) {
    var errorLengthStatus by rememberSaveable { mutableStateOf(ErrorStatus.INITIAL) }
    var errorRegexStatus by rememberSaveable { mutableStateOf(ErrorStatus.INITIAL) }
    val errorStates = mutableMapOf("Length" to errorLengthStatus, "Regex" to errorRegexStatus)

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        SignUpCustomTextField(painterResource, placeholder) {newStates ->
            errorLengthStatus = newStates["Length"] ?: ErrorStatus.INITIAL
            errorRegexStatus = newStates["Regex"] ?: ErrorStatus.INITIAL
        }
        InformationTextContainer(placeholder, errorRegexStatus)
    }
}

/*
*   Передавать InformationTextContainer errorStates.
*   В самой функции InformationTextContainer проверять тип.
*   Если тип Логин или Пароль, то первому елементу в InformationTextContainer передавать первый
*   елемент errorStates, второму - второй элемент errorStates.
*   Если тип Почта, то устанавливать второй элемент errorStates в errorStatus.
* */

@Composable
fun InformationTextContainer(type: String, errorStatus: ErrorStatus) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 9.dp, top = 24.dp, end = 9.dp)
    ) {
        getErrorMessages(type).forEach { text ->
            if(text == getErrorMessages(type).first()) {
                InformationText(text = text, Modifier, errorStatus)
            } else {
                InformationText(text = text, Modifier.padding(top = 18.dp), errorStatus)
            }
        }
    }
}

@Composable
fun InformationText(text: String, modifier: Modifier, errorStatus: ErrorStatus) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_list_point),
            contentDescription = "List",
            tint = when(errorStatus) {
                ErrorStatus.INITIAL -> colorOnSecondary
                ErrorStatus.ERROR -> colorOnError
                else -> colorOnTertiary
            },
            modifier = Modifier
                .align(Alignment.Top)
                .padding(top = 8.dp, end = 8.dp)
        )
        LabelTextComponent(value = text, errorStatus)
    }
}

fun checkLength(text: String, type: String): ErrorStatus {
    if (text.isEmpty()) return ErrorStatus.INITIAL

    return if(type == "Логин") {
        if (text.length in 4..15) {
            ErrorStatus.VALIDATED
        } else ErrorStatus.ERROR
    } else {
        if (text.length >= 8) {
            ErrorStatus.VALIDATED
        } else ErrorStatus.ERROR
    }
}

fun checkRegex(text: String, regex: Regex): ErrorStatus {
    return when {
        text.isEmpty() -> ErrorStatus.INITIAL
        text.matches(regex) -> ErrorStatus.VALIDATED
        else -> ErrorStatus.ERROR
    }
}

@Composable
fun SignUpCustomTextField(
    painterResource: Painter,
    placeholder: String,
    onStateChange: (MutableMap<String, ErrorStatus>) -> Unit
) {
    var textValue by rememberSaveable { mutableStateOf("") }
    var isFocused by rememberSaveable { mutableStateOf(false) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    var errorLengthStatus by rememberSaveable { mutableStateOf(ErrorStatus.INITIAL) }
    var errorRegexStatus by rememberSaveable { mutableStateOf(ErrorStatus.INITIAL) }

    val states = mutableMapOf("Length" to errorLengthStatus, "Regex" to errorRegexStatus)

    val regex = RegexObject.getRegex(placeholder)

    BasicTextField(
        value = textValue,
        onValueChange = {
            textValue = it

            // TODO: Написать функцию для проверки условий
            if(regex != null) {
                when(placeholder) {
                    "Логин" -> {
                        errorLengthStatus = checkLength(textValue, "Логин")
                        errorRegexStatus = checkRegex(textValue, regex)

                        states["Length"] = errorLengthStatus
                        states["Regex"] = errorRegexStatus

                        onStateChange(states)
                    }
                    "Почта" -> {
                        errorRegexStatus = checkRegex(textValue, regex)

                        states["Regex"] = errorRegexStatus

                        onStateChange(states)
                    }
                    else -> {
                        errorLengthStatus = checkLength(textValue, "Пароль")
                        errorRegexStatus = checkRegex(textValue, regex)

                        states["Length"] = errorLengthStatus
                        states["Regex"] = errorRegexStatus

                        onStateChange(states)
                    }
                }
            } else {
                // TODO: Сделать проверку для пароля
            }
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        textStyle = TextStyle(
            fontSize = 18.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.W300,
            color = colorOnSecondary
        ),
        cursorBrush = SolidColor(colorLink),
        keyboardOptions = KeyboardOptions(
            keyboardType = when(placeholder) {
                stringResource(id = R.string.login_label) -> KeyboardType.Text
                stringResource(id = R.string.email_label) -> KeyboardType.Email
                stringResource(id = R.string.password_label) -> KeyboardType.Password
                else -> KeyboardType.Password
            },
            imeAction = if (placeholder == stringResource(id = R.string.confirm_password_label)) {
                ImeAction.Done
            } else ImeAction.Next
        ),
        visualTransformation = if (placeholder == stringResource(id = R.string.login_label) ||
            placeholder == stringResource(id = R.string.email_label)
        ) {
            VisualTransformation.None
        } else {
            if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
        },
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .background(
                        color = colorBackground,
                        shape = RoundedCornerShape(ComponentsShape.getShapeDp("large"))
                    )
                    .border(
                        width = 1.dp,
                        color = if (isFocused) {
                            colorLink
                        } else if (textValue.isNotEmpty()) {
                            colorOnSecondary
                        } else {
                            colorNotActive
                        },
                        shape = RoundedCornerShape(ComponentsShape.getShapeDp("large"))
                    )
                    .padding(vertical = 13.dp, horizontal = 16.dp) // Inner padding
            ) {
                Icon(
                    painter = painterResource,
                    contentDescription = "",
                    tint = colorNotActive,
                    modifier = Modifier
                        .padding(end = 10.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    if (textValue.isEmpty()) {
                        NormalTextComponent(placeholder)
                    }
                    innerTextField()
                }

                if (placeholder == stringResource(id = R.string.password_label) ||
                    placeholder == stringResource(id = R.string.confirm_password_label)
                ) {
                    val iconResourceId = if (passwordVisible) R.drawable.ic_visible else R.drawable.ic_invisible

                    IconButton(
                        onClick = { passwordVisible = !passwordVisible },
                        modifier = Modifier
                            .height(24.dp)
                            .width(26.dp)
                            .wrapContentWidth(align = Alignment.End)
                            .padding(start = 8.dp)
                            .weight(0.18f)
                            .align(Alignment.CenterVertically)
                    ) {
                        Icon(
                            painter = painterResource(iconResourceId),
                            contentDescription = "",
                            tint = colorNotActive
                        )
                    }
                }
            }
        }
    )
}