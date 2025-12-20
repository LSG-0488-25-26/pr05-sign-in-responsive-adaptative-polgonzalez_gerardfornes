package com.example.loginregister.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.loginregister.Routes
import com.example.loginregister.models.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StopifyViewModel : ViewModel() {

    var email by mutableStateOf("")
        private set
    var contrasenya by mutableStateOf("")
        private set
    var contrasenyaVisible by mutableStateOf(false)
        private set
    var cargando by mutableStateOf(false)
        private set

    fun emailCargando(newEmail: String){
        email = newEmail
    }

    fun contrasenyaCargando(newContrasenya: String){
        contrasenya = newContrasenya
    }

    fun alternarContrasenyaVisibile(){
        contrasenyaVisible = !contrasenyaVisible
    }

    fun loginSeleccionat(navController: NavController){
        viewModelScope.launch{
            cargando = true
            delay(2000)
            cargando = false
            navController.navigate(Routes.HomeScreen.route) {
                popUpTo(Routes.LoginLayout.route){inclusive = true}
            }
        }
    }


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