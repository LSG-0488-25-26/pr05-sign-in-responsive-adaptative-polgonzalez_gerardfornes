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

    // Para el Login
    var email by mutableStateOf("")
        private set
    var contrasenya by mutableStateOf("")
        private set
    var contrasenyaVisible by mutableStateOf(false)
        private set
    var cargando by mutableStateOf(false)
        private set

    // Para el Registro
    var nomRegistre by mutableStateOf("")
        private set
    var emailRegistre by mutableStateOf("")
        private set
    var telefonRegistre by mutableStateOf("")
        private set
    var cumpleRegistre by mutableStateOf("")
        private set
    var usuariRegistre by mutableStateOf("")
        private set
    var contrasenyaRegistre by mutableStateOf("")
        private set
    var confirmarContrasenyaRegistre by mutableStateOf("")
        private set
    var acceptarTermesRegistre by mutableStateOf(false)
        private set
    var missatgeError by mutableStateOf<String?>(null)
        private set

    //Funcions per el Login
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
    // Funcions per el Registre

    fun nomRegistreChange(it: String) { nomRegistre = it }
    fun emailRegistreChange(it: String) { emailRegistre = it }
    fun telefonRegistreChange(it: String) {
        if (it.all { char -> char.isDigit() }) telefonRegistre = it
    }
    fun cumpleRegistreChange(it: String) { cumpleRegistre = it }
    fun usuariRegistreChange(it: String) { usuariRegistre = it }
    fun contrasenyaRegistreChange(it: String) { contrasenyaRegistre = it }
    fun confirmarContrasenyaRegistreChange(it: String) { confirmarContrasenyaRegistre = it }
    fun termesRegistreChange(it: Boolean) { acceptarTermesRegistre = it }

    fun registerSeleccionat(navController: NavController) {
        // 1. Validaciones
        if (nomRegistre.isBlank() || emailRegistre.isBlank() || telefonRegistre.isBlank() || usuariRegistre.isBlank() || contrasenyaRegistre.isBlank()) {
            missatgeError = "Tots els camps són obligatoris"
            return
        }
        if (!emailRegistre.contains("@")) {
            missatgeError = "El format del correu no és vàlid"
            return
        }
        if (contrasenyaRegistre != confirmarContrasenyaRegistre) {
            missatgeError = "Les contrasenyes no coincideixen"
            return
        }
        if (!acceptarTermesRegistre) {
            missatgeError = "Has d'acceptar els termes i condicions"
            return
        }

        viewModelScope.launch {
            missatgeError = null
            cargando = true
            delay(2000)

            val newUser = User(
                fullName = nomRegistre,
                email = emailRegistre,
                phone = telefonRegistre,
                birthDate = cumpleRegistre,
                username = usuariRegistre,
                password = contrasenyaRegistre,
                termsAccepted = acceptarTermesRegistre
            )
            setCurrentUser(newUser)

            cargando = false
            // Navegar a Home
            navController.navigate(Routes.HomeScreen.route) {
                popUpTo(Routes.LoginLayout.route) { inclusive = true }
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