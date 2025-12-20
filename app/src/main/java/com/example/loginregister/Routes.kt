package com.example.loginregister

sealed class Routes(val route: String) {
    object HomeScreen : Routes("home")
    object LoginLayout : Routes("login")
    object RegistreScreen : Routes("register")

}