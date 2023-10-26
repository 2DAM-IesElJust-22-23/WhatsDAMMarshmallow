package com.example.whatsdam

import java.text.SimpleDateFormat
import java.util.Date

object MessageDataSet {
    val messages = mutableListOf<Missatge>()

    fun addMessage(user: String, text: String, hora: String) {
        val dateFormat = SimpleDateFormat("HH:mm")
        val horaActual = Date()
        val horaFormatada = dateFormat.format(horaActual)

        val message = Missatge(user, text, horaFormatada)
        messages.add(message)
    }
    fun FindAllMessage(){

    }
}
