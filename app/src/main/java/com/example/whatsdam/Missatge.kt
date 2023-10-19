package com.example.whatsdam

import java.text.SimpleDateFormat
import java.util.Date

data class Missatge(val usuari: String, val text: String, val hora:String)
object MessageDataSet {
    val messages = mutableListOf<Missatge>()

    fun addMessage(user: String, text: String) {
        val dateFormat = SimpleDateFormat("HH:mm")
        val horaActual = Date()
        val horaFormatada = dateFormat.format(horaActual)

        val message = Missatge(user, text, horaFormatada)
        messages.add(message)
    }
}
