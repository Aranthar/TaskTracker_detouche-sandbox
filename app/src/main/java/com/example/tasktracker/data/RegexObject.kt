package com.example.tasktracker.data

object RegexObject {
//    private val loginRegex: Regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(\\d?)+.{0,15}$")
    private val loginRegex: Regex = Regex("\\w{0,15}$")
    private val emailRegex: Regex = Regex("""(\w)+@(\w)+\.(\w)+""")
    private val passwordRegex: Regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,100}\$")
    fun getRegex(type: String): Regex {
        return when(type) {
            "Логин" -> loginRegex
            "Почта" -> emailRegex
            else -> passwordRegex
        }
    }
}