package com.example.whatsdam.model

import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.util.Date

object  MessageDataSet {

    private val _messages = MutableLiveData<ArrayList<Message>>().apply{
        value= ArrayList<Message>()
    }

    val messages:MutableLiveData<ArrayList<Message>> = _messages
    fun deleteMessage(username: String, text: String) {
        messages.value?.removeIf { it.usuari == username && it.text == text }
    }
    fun addMessage(msg: Message){
        messages.value?.add(msg)
    }

    // Retorna el numero de missatges
    fun getNumMessages() = messages.value?.size
    fun getMenssage(int: Int) = messages.value?.get(int)
    fun getsize(): Int? {
        return messages.value?.size
    }
}
