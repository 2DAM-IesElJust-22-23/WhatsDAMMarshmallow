package com.example.whatsdam.model

import java.text.SimpleDateFormat
import java.util.Date

object MessageDataSet {
    val messages = mutableListOf<Message>()

    fun addMessage(user: String, text: String, hora: String) {
        val dateFormat = SimpleDateFormat("HH:mm")
        val horaActual = Date()
        val horaFormatada = dateFormat.format(horaActual)

        val message = Message(user, text, horaFormatada)
        messages.add(message)
    }
    fun deleteMessage(username: String, text: String) {
        messages.removeIf { it.usuari == username && it.text == text }
    }
    fun addMessage(msg: Message){
        messages.add(msg)
    }
    // Llista de missatges
    fun getMessages() = messages

    // Retorna el numero de missatges
    fun getNumMessages() = messages.size
    fun getMenssage(int: Int) = messages.get(int)
    fun deleteMessage() = messages
}
