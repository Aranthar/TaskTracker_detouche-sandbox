package com.example.tasktracker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tasktracker.R
import com.example.tasktracker.components.functions.HeadingTextComponent
import com.example.tasktracker.components.functions.PasswordConfirmFiledComponent
import com.example.tasktracker.components.functions.PasswordFiledComponent
import com.example.tasktracker.components.functions.SignInUpButton
import com.example.tasktracker.components.functions.TextFiledComponent
import com.example.tasktracker.viewModels.SignUpViewModel

private val viewModel = SignUpViewModel()

@Composable
fun SignUpScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 29.dp, vertical = 75.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            HeadingTextComponent(
                value = stringResource(id = R.string.header_text_registration),
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp)
            )
            TextFiledComponent(
                labelValue = stringResource(id = R.string.login_label),
                painterResource = painterResource(id = R.drawable.login_icon),
                viewModel
            )
            TextFiledComponent(
                labelValue = stringResource(id = R.string.email_label),
                painterResource = painterResource(id = R.drawable.email_icon),
                viewModel
            )
            PasswordFiledComponent(
                labelValue = stringResource(id = R.string.password_label),
                painterResource = painterResource(id = R.drawable.lock_icon),
                viewModel
            )
            PasswordConfirmFiledComponent(
                labelValue = stringResource(id = R.string.confirm_password_label),
                painterResource = painterResource(id = R.drawable.lock_icon),
                viewModel
            )
            androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(71.dp))
            // TODO: сделать функцию регистрации
            SignInUpButton(stringResource(R.string.text_registration), {  }, viewModel.infoAboutRegistration())
            // TODO: сделать функцию перехода на активити авторизации
            SignInUpButton(stringResource(R.string.text_account), {  }, true)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewOfSignUpScreen() {
    SignUpScreen()
}