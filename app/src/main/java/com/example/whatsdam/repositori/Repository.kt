package com.example.whatsdam.repositori

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.communications.CommunicationManager
import com.example.whatsdam.model.Message
import com.example.whatsdam.model.MessageDataSet
import org.json.JSONObject

class MessagesRepository private constructor() { // Constructor privat

    // Propietats del repositori

    var username="Casc Fosc"
    var server="192.168.1.1"
    val port=9999
    var listenport:Int?=null;
    val cm=CommunicationManager()

    private val _messages = MutableLiveData<ArrayList<Message>>().apply{
        value= ArrayList<Message>()
    }

    val messages: MutableLiveData<ArrayList<Message>> = _messages
    fun getmessages(): ArrayList<Message> {
        return messages.value!!
    }
    fun addMessage(msg: Message){
        messages.value?.add(msg)
    }
    fun getsize(): Int? {
        return messages.value?.size
    }
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
    suspend fun login() : JSONObject {
        Log.d("MessageRepository", "Username: $username")
        Log.d("MessageRepository", "Server IP: $server")
        // Lógica para iniciar sesión utilizando CommunicationManager
        val loginResponse = cm.login(username, server)
        // Devolver la respuesta del servidor
        return loginResponse
    }
    suspend fun sendMessage(msg: Message){
        cm.sendServer(toJsonComand(msg).toString())
    }
    // Mètodes que ofereix aquest API del repositori:
    fun getMenssage(int: Int) = MessageDataSet.getMenssage(int)
    fun getNumMessages() = MessageDataSet.getNumMessages()
    fun add(msg: Message) = MessageDataSet.addMessage(msg)
    fun deleteMessage(username: String, text: String) {
        MessageDataSet.deleteMessage(username, text)
    }
    fun toJsonComand(msg: Message):JSONObject{
        val jso = JSONObject()
        jso.put("command", "newMessage")
        jso.put("user", msg.text)
        jso.put("content", msg.text)
        return jso
    }

}