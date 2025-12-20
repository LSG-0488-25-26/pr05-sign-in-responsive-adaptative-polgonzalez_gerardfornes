package com.example.loginregister.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginregister.models.User

class StopifyViewModel : ViewModel() {

    private val _currentUser = MutableLiveData<User?>()

    val currentUser: LiveData<User?> = _currentUser

    init {
        // Simulem un usuari de prova a l'inici per veure que funciona
        _currentUser.value = User(
            fullName = "Usuari De Prova",
            birthDate = "01/01/2000",
            email = "test@stopify.com",
            phone = "666666666",
            username = "StopifyFan", // Aquest nom es veurà a la Home
            password = "123",
            termsAccepted = true
        )
    }

    fun setCurrentUser(user: User) {
        _currentUser.value = user
    }
}