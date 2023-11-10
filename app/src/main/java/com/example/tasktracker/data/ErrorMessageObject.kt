package com.example.tasktracker.data

object ErrorMessageObject {
    private val loginErrorMessages = arrayOf("Логин должен содержать только латинские буквы, цифры и не превышать длинну в 15 символов",
        "Данный логин уже существует")
    private val emailErrorMessages = arrayOf("Некорректный домен почты", "Данная почта уже существует")
    private val passwordErrorMessages = arrayOf("Минимальное количество символов - 8",
        "Пароль должен содержать только латинские буквы и цифры")

    fun getErrorMessages(type: String): Array<String> {
        return when(type) {
            "Логин" -> loginErrorMessages
            "Почта" -> emailErrorMessages
            else -> passwordErrorMessages
        }
    }
}