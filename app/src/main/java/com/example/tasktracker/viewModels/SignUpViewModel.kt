package com.example.tasktracker.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

class SignUpViewModel: ViewModel() {
    internal var passwordForCheck by  mutableStateOf("")

    private var login by  mutableStateOf(false)
    private var email by  mutableStateOf(false)
    private var password by  mutableStateOf(false)
    private var confirmPassword by  mutableStateOf(false)

    fun changePasswordForCheck(input: String) {
        passwordForCheck = input
    }

    fun changeLogin(input: Boolean) {
        login = input
    }

    fun changeEmail(input: Boolean) {
        email = input
    }

    fun changePassword(input: Boolean) {
        password = input
    }

    fun changeConfirmPassword(input: Boolean) {
        confirmPassword = input
    }

    fun infoAboutRegistration(): Boolean {
        val info = listOf(login, email, password, confirmPassword)
        var sum = 0
        info.map { if(it) sum++ }
        return sum == 4
    }
}