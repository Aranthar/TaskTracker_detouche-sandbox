package com.example.tasktracker.components.fragments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tasktracker.R
import com.example.tasktracker.components.components.ComponentsShape.getShapeDp
import com.example.tasktracker.components.components.CustomProgressBar
import com.example.tasktracker.components.components.IconButton
import com.example.tasktracker.components.components.TextButton
import com.example.tasktracker.components.components.TextFieldInformationContainer

//private val viewModel = SignUpViewModel()

// TODO: Отформатировать под проект.
@Composable
fun SignUpLoginEmailFragment(onNextClicked: () -> Unit) {
    TextFieldInformationContainer(
        painterResource(R.drawable.ic_login),
        stringResource(id = R.string.login_label)
    )
    Spacer(modifier = Modifier.height(61.dp))

    TextFieldInformationContainer(
        painterResource(R.drawable.ic_mail),
        stringResource(id = R.string.email_label)
    )
    Spacer(modifier = Modifier.height(131.dp))

    TextButton(
        { onNextClicked() },
        stringResource(id = R.string.next)
    )
}

@Composable
fun SignUpPasswordFragment(onBackClicked: () -> Unit) {
    TextFieldInformationContainer(
        painterResource(R.drawable.ic_lock),
        stringResource(id = R.string.password_label)
    )
    Spacer(modifier = Modifier.height(47.dp))

    TextFieldInformationContainer(painterResource(
        R.drawable.ic_lock),
        stringResource(id = R.string.confirm_password_label)
    )
    Spacer(modifier = Modifier.height(131.dp))

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(
            { onBackClicked() },
            painterResource(id = R.drawable.ic_back),
            modifier = Modifier.weight(0.2f),
            shape = RoundedCornerShape(
                topStart = getShapeDp("large"),
                bottomStart = getShapeDp("large")
            )
        )

        Spacer(modifier = Modifier.width(3.dp))

        TextButton(
            { onBackClicked() },
            stringResource(id = R.string.text_registration),
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(
                topEnd = getShapeDp("large"),
                bottomEnd = getShapeDp("large")
            )
        )
    }
}

@Composable
fun SignUpFragmentManager() {
    var currentFragment by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomProgressBar(currentFragment)

        Spacer(modifier = Modifier.height(44.dp))

        when (currentFragment) {
            0 -> SignUpLoginEmailFragment {
                currentFragment = 1
            }
            1 -> SignUpPasswordFragment {
                currentFragment = 0
            }
        }
    }
}