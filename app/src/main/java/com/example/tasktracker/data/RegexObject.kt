package com.example.tasktracker.data

object RegexObject {
    private val loginRegex: Regex = Regex("[a-zA-Z0-9]+", setOf(RegexOption.IGNORE_CASE))
    private val emailRegex: Regex = Regex("""^[\w-.]+@([\w-]+\.)+[\w-]+""", setOf(RegexOption.IGNORE_CASE))
    private val passwordRegex: Regex =
        Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#%^&*()_+\\-=:;”’{}<>?/|`~,.])[a-zA-Z\\d!@#%^&*()_+\\-=:;”’{}<>?/|`~,.]{8,100}\$")
    fun getRegex(type: String): Regex? {
        return when(type) {
            "Логин" -> loginRegex
            "Почта" -> emailRegex
            "Пароль" -> passwordRegex
            else -> null
        }
    }
}