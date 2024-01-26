package com.example.tasktracker.components.functions

import android.content.Context
import android.widget.Toast

// Check DataBase Error
private fun checkDatabaseForProfile(): Boolean {
    return false
}

fun getInfoAboutDatabase(input: String): Boolean {
    return checkDatabaseForProfile()
}

fun showMessage(context: Context, message:String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}