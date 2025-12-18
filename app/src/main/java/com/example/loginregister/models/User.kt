package com.example.loginregister.models

data class User(
    val fullName: String,
    val birthDate: String,
    val email: String,
    val phone: String,
    val username: String,
    val password: String,
    val termsAccepted: Boolean
)