package com.example.tasktracker.data

object ErrorMessageObject {
    private val loginErrorMessages = arrayOf("От 4 до 15 символов", "Только латинские буквы и цифры")
    private val emailErrorMessages = arrayOf("Корректный домен почты")
    private val passwordErrorMessages = arrayOf("Минимальная длина 8 символов",
        "Латинские буквы, цифры и специальные символы")
    private val confirmPasswordErrorMessages = arrayOf("Пароли совпадают")

    fun getErrorMessages(type: String): Array<String> {
        return when(type) {
            "Логин" -> loginErrorMessages
            "Почта" -> emailErrorMessages
            "Пароль" -> passwordErrorMessages
            else -> confirmPasswordErrorMessages
        }
    }
}