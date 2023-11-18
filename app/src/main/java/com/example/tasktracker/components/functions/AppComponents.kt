package com.example.tasktracker.components.functions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tasktracker.R
import com.example.tasktracker.components.componentsShape
import com.example.tasktracker.data.ErrorMessageObject
import com.example.tasktracker.data.RegexObject
import com.example.tasktracker.ui.theme.colorActiveButton
import com.example.tasktracker.ui.theme.colorBackground
import com.example.tasktracker.ui.theme.colorNotActiveButton
import com.example.tasktracker.ui.theme.colorOnError
import com.example.tasktracker.ui.theme.colorOnSecondary
import com.example.tasktracker.ui.theme.colorOnTertiary
import com.example.tasktracker.ui.theme.colorPrimary
import com.example.tasktracker.ui.theme.colorTextNotActiveButton
import com.example.tasktracker.viewModels.SignUpViewModel

@Composable
fun HeadingTextComponent(value: String, modifier: Modifier) {
    Text(
        text = value,
        modifier = modifier
            .fillMaxWidth()
            .heightIn()
            .paddingFromBaseline(0.dp, 55.dp)
            .background(MaterialTheme.colorScheme.background),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSecondary
    )
}

@Composable
fun NormalTextComponent(value: String) {
    Text(
        text = value,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = MaterialTheme.typography.bodyMedium,
    )
}

@Composable
fun LabelTextComponent(value: String, viewModel: SignUpViewModel) {
//    var textHeight by remember { mutableStateOf(0.dp) }
    val context = LocalDensity.current
//    viewModel.changeHeightLabel(textHeight)

    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn()
//            .onGloballyPositioned { coordinates ->
//                val height = with(context) {
//                    coordinates.size.height.toDp()
//                }
//                textHeight = height
//                Log.d("ANIME", textHeight.toString())
//            }
            .background(MaterialTheme.colorScheme.background),
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.error
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFiledComponent(labelValue: String, painterResource: Painter, viewModel: SignUpViewModel) {
    val errorMessageMassive = ErrorMessageObject.getErrorMessages(labelValue)
    val bufErrorMassive = mutableListOf<String>()
    val regex = RegexObject.getRegex(labelValue)

    var textValue by rememberSaveable { mutableStateOf("") }
    var isError by rememberSaveable { mutableStateOf(false) }
    var errorMessage by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = textValue,
        onValueChange = {
            textValue = it

            if (getInfoAboutDatabase(labelValue)) {
                isError = true
                errorMessage = errorMessageMassive.last()
            } else {
                /*
                    If the validation check is successful and the requirements are met,
                    we get the reverse value so as not to show an error when processing is successful
                */
                when {
                    !textValue.matches(regex) -> {
                        isError = true
                        if (!bufErrorMassive.contains(errorMessageMassive[0])) bufErrorMassive.add(errorMessageMassive[0])
                        errorMessage = bufErrorMassive.joinToString("||")
                    }
                    textValue.matches(regex) -> {
                        if (bufErrorMassive.contains(errorMessageMassive[0])) {
                            bufErrorMassive.removeAt(bufErrorMassive.indexOf(errorMessageMassive[0]))
                        }
                    }
                }

                if(labelValue == "Логин") {
                    when {
                        textValue.length > 15 -> {
                            isError = true
                            if (!bufErrorMassive.contains(errorMessageMassive[1])) bufErrorMassive.add(errorMessageMassive[1])
                            errorMessage = bufErrorMassive.joinToString("||")
                        }

                        textValue.length < 15 -> {
                            if (bufErrorMassive.contains(errorMessageMassive[1])) {
                                bufErrorMassive.removeAt(bufErrorMassive.indexOf(errorMessageMassive[1]))
                            }
                        }
                    }
                }

                if(labelValue == "Логин") {
                    if(bufErrorMassive.size == 0) {
                        isError = false
                        viewModel.changeLogin(true)
                    } else {
                        isError = true
                        viewModel.changeLogin(false)
                    }
                } else {
                    if(bufErrorMassive.size == 0) {
                        isError = false
                        viewModel.changeEmail(true)
                    } else {
                        isError = true
                        viewModel.changeEmail(false)
                    }
                }
            }
        },
        label = {
            Text(
                text = labelValue,
                color = when {
                    !isError && textValue.isNotEmpty() -> colorOnTertiary
                    isError -> colorOnError
                    else -> Color.LightGray
                }
            )
        },
        isError = isError,
        supportingText = {
            if (isError) {
                Column {
                    errorMessage.split("||").forEach { text ->
                        LabelTextComponent(text, viewModel)
                    }
                }
            }
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
        shape = componentsShape.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordFiledComponent(labelValue: String, painterResource: Painter, viewModel: SignUpViewModel) {
    val errorMessageMassive = ErrorMessageObject.getErrorMessages(labelValue)
    val bufErrorMassive = mutableListOf<String>()
    val regex = RegexObject.getRegex(labelValue)

    var passwordValue by rememberSaveable { mutableStateOf("") }
    val passwordVisible = rememberSaveable { mutableStateOf(false) }
    var isError by rememberSaveable { mutableStateOf(false) }
    var errorMessage by rememberSaveable { mutableStateOf("") }


    OutlinedTextField(
        value = passwordValue,
        onValueChange = {
            passwordValue = it
            viewModel.changePasswordForCheck(passwordValue)

            /*
                If the validation check is successful and the requirements are met,
                we get the reverse value so as not to show an error when processing is successful
            */

            when {
                passwordValue.length < 8 -> {
                    if (!bufErrorMassive.contains(errorMessageMassive[0])) bufErrorMassive.add(errorMessageMassive[0])
                    errorMessage = bufErrorMassive.joinToString("||")
                }

                passwordValue.length > 8 -> {
                    if (bufErrorMassive.contains(errorMessageMassive[0])) {
                        bufErrorMassive.removeAt(bufErrorMassive.indexOf(errorMessageMassive[0]))
                    }
                }
            }

            when {
                !passwordValue.matches(regex) -> {
                    if (!bufErrorMassive.contains(errorMessageMassive[1])) bufErrorMassive.add(errorMessageMassive[1])
                    errorMessage = bufErrorMassive.joinToString("||")
                }
                passwordValue.matches(regex) -> {
                    if (bufErrorMassive.contains(errorMessageMassive[1])) {
                        bufErrorMassive.removeAt(bufErrorMassive.indexOf(errorMessageMassive[1]))
                    }
                }
            }

            if(bufErrorMassive.size == 0) {
                isError = false
                viewModel.changePassword(true)
            } else {
                isError = true
                viewModel.changePassword(false)
            }
        },
        label = {
            Text(
                text = labelValue,
                color = when {
                    !isError && passwordValue.isNotEmpty() -> colorOnTertiary
                    isError -> colorOnError
                    else -> Color.LightGray
                }
            )
        },
        isError = isError,
        supportingText = {
            if (isError) {
                Column {
                    errorMessage.split("||").forEach { text ->
                        LabelTextComponent(text, viewModel)
                    }
                }
            }
        },
        trailingIcon = {
            val iconImage = if(passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            val description = if(passwordVisible.value) {
                stringResource(R.string.hide_password)
            } else {
                stringResource(R.string.show_password)
            }
            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description, tint = Color.LightGray)
            }
        },
        visualTransformation = if(passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = if(!isError && passwordValue.isNotEmpty()) {
                colorOnTertiary
            } else colorPrimary,
            focusedLabelColor = colorPrimary,
            unfocusedBorderColor = if(!isError && passwordValue.isNotEmpty()) {
                colorOnTertiary
            } else Color.LightGray,
            cursorColor = colorPrimary,
            containerColor = colorBackground
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        shape = componentsShape.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordConfirmFiledComponent(labelValue: String, painterResource: Painter, viewModel: SignUpViewModel) {
    val errorMessageMassive = ErrorMessageObject.getErrorMessages(labelValue)
    val localFocusManager = LocalFocusManager.current


    var passwordValue by rememberSaveable { mutableStateOf("") }
    val passwordVisible = rememberSaveable { mutableStateOf(false) }
    var isError by rememberSaveable { mutableStateOf(false) }
    var errorMessage by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = passwordValue,
        onValueChange = {
            passwordValue = it

            isError = passwordValue != viewModel.passwordForCheck
            viewModel.changeConfirmPassword(!isError)
            if (isError) errorMessage = errorMessageMassive[0]
        },
        label = {
            Text(
                text = labelValue,
                color = when {
                    !isError && passwordValue.isNotEmpty() -> colorOnTertiary
                    isError -> colorOnError
                    else -> Color.LightGray
                }
            )
        },
        isError = isError,
        supportingText = {
            if (isError) LabelTextComponent(errorMessage, viewModel)
        },
        trailingIcon = {
            val iconImage = if(passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            val description = if(passwordVisible.value) {
                stringResource(R.string.hide_password)
            } else {
                stringResource(R.string.show_password)
            }
            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description, tint = Color.LightGray)
            }
        },
        visualTransformation = if(passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = if(!isError && passwordValue.isNotEmpty()) {
                colorOnTertiary
            } else colorPrimary,
            focusedLabelColor = colorPrimary,
            unfocusedBorderColor = if(!isError && passwordValue.isNotEmpty()) {
                colorOnTertiary
            } else Color.LightGray,
            cursorColor = colorPrimary,
            containerColor = colorBackground
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        shape = componentsShape.medium,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun SignInUpButton(text: String, onClick: () -> Unit, enable: Boolean) {
    Button(
        onClick = onClick,
        enabled = enable,
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = colorNotActiveButton,
            disabledContentColor = colorTextNotActiveButton,
            containerColor = colorActiveButton,
            contentColor = colorOnSecondary
            ),
        shape = componentsShape.medium,
        modifier = Modifier
            .padding(bottom = 20.dp)
            .heightIn(50.dp, 50.dp)
            .fillMaxWidth()
    ) {
        NormalTextComponent(text)
    }
}