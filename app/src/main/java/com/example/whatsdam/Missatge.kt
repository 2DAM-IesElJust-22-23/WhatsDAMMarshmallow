package com.example.whatsdam

data class Missatge(val usuari: String, val text: String)
object MessageDataSet {
    val messages = mutableListOf<Missatge>()

    fun addMessage(user: String, text: String) {
        val message = Missatge(user, text)
        messages.add(message)
    }
}