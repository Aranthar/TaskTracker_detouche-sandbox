package com.example.tasktracker.components.functions

private fun defineFieldType(type: String): Regex {
    val loginRegularExpression = Regex("^(?=.*[a-z])(?=.*[A-Z])(\\d?)+.{1,15}$")
    val mailRegularExpression = Regex("""(\w)+@(\w)+\.(\w)+""")
    val passwordRegularExpression = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,100}\$")

    // TODO: написать функцию определения типа поля
    return Regex("""""")
}

private fun complianceCheck() {
    // TODO: написать функцию оценки выполнения требований
}

fun getInfoAboutDefineFieldType(type: String): Boolean {
    // TODO: написать функцию вызова функции оценки
    return false
}