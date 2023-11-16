package com.example.whatsdam.repositori

import com.example.whatsdam.model.Message
import com.example.whatsdam.model.MessageDataSet

class MessagesRepository private constructor() { // Constructor privat

    // Propietats del repositori

    val username="Casc Fosc"       // nom de l'usuari
    val server="192.168.1.1"    // adreça IP del servidor

    // Implementacio del Singleton
    companion object {
        // Referencia a la propia instancia de la classe
        private var INSTANCE: MessagesRepository? = null

        // Metode per obtenir la referencia a aquesta instancia
        fun getInstance(): MessagesRepository {
            if (INSTANCE == null) {
                INSTANCE = MessagesRepository()
            }
            return INSTANCE!!
        }
    }

    // Mètodes que ofereix aquest API del repositori:
    fun getMenssage(int: Int) = MessageDataSet.getMenssage(int)
    fun getMessages() = MessageDataSet.getMessages()
    fun getNumMessages() = MessageDataSet.getNumMessages()
    fun add(msg: Message) = MessageDataSet.addMessage(msg)
    fun deleteMessage(username: String, text: String) {
        MessageDataSet.deleteMessage(username, text)
    }

}