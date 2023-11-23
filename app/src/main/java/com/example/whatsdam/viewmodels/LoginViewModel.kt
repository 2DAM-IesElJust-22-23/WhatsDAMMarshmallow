package com.example.whatsdam.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.whatsdam.repositori.MessagesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class LoginViewModel : ViewModel() {
    private val repository = MessagesRepository.getInstance()

    // Propietats MutableLiveData per a l'estat de la connexió
    private val _loginStatus = MutableLiveData<JSONObject>()
    val loginStatus get() = _loginStatus

    // Mètode per realitzar el login al servidor de manera asíncrona
    fun login() {
        val username = repository.username
        val server = repository.server

        // Utilitzem viewModelScope per gestionar la corrutina vinculada al cicle de vida del ViewModel
        GlobalScope.launch(Dispatchers.IO) {
            try {
                // Valida el nom d'usuari abans de fer la crida al servidor
                val isUsernameValid = username.isNotEmpty()

                if (isUsernameValid) {
                    // Executem la crida al servidor a través del repositori
                    val loginResponse = repository.login()

                    // Actualitzem el LiveData amb la resposta del servidor
                    _loginStatus.postValue(loginResponse)
                } else {
                    // En cas d'error de validació, actualitzem el LiveData amb un objecte d'error
                    val validationError = JSONObject("""{"status": "error", "message": "Invalid username"}""")
                    _loginStatus.postValue(validationError)
                }
            } catch (e: Exception) {
                // En cas d'altres errors, actualitzem el LiveData amb un objecte d'error
                val errorObject = JSONObject("""{"status": "error", "message": "${e.message}"}""")
                _loginStatus.postValue(errorObject)
            }
        }
    }
}
