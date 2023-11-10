package com.example.tasktracker.components.functions

// Check Regex Error
private fun defineFieldType(type: String): Regex {
    return when (type) {
        "Логин" -> Regex("^(?=.*[a-z])(?=.*[A-Z])(\\d?)+.{1,15}$")
        "Пароль" -> Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,100}\$")
        else -> Regex("")
    }
}

// Check DataBase Error
private fun checkDatabaseForProfile(): Boolean {
    return false
}

fun getInfoAboutDatabase(input: String): Boolean {
    return checkDatabaseForProfile()
}