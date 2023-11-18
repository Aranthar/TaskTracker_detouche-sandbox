package com.example.tasktracker.data

private val point = String(Character.toChars(0x2022))

object ErrorMessageObject {
    private val loginErrorMessages = arrayOf("$point Логин должен содержать только латинские буквы и цифры",
        "$point Максимальная длина логина - 15 символов",
        "$point Данный логин уже существует"
    )
    private val emailErrorMessages = arrayOf("$point Некорректный домен почты", "$point Данная почта уже существует")
    private val passwordErrorMessages = arrayOf("$point Минимальное количество символов - 8",
        "$point Пароль должен содержать маленькие и большие латинские буквы, а также цифры и спец. символы")
    private val confirmPasswordErrorMessages = arrayOf("$point Пароли не совпадают")

    fun getErrorMessages(type: String): Array<String> {
        return when(type) {
            "Логин" -> loginErrorMessages
            "Почта" -> emailErrorMessages
            "Пароль" -> passwordErrorMessages
            else -> confirmPasswordErrorMessages
        }
    }
}