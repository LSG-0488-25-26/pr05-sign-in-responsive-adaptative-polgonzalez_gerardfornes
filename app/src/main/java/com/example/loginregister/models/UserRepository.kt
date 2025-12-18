package com.example.stopify.model

import com.example.loginregister.models.User

object UserRepository {

    private val users = mutableListOf<User>()

    fun addUser(user: User): Boolean {
        //Comprovar si l'usuari ja existeix per no repetir
        val exists = users.any { it.username == user.username }
        if (exists) {
            return false
        }
        users.add(user)
        return true // Guardat amb èxit
    }

    // Funció per comprovar el Login
    fun findUser(username: String, pass: String): User? {
        return users.find { it.username == username && it.password == pass }
    }
}