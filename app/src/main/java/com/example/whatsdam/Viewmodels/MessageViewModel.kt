package com.example.whatsdam.Viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.whatsdam.model.Message
import com.example.whatsdam.repositori.MessagesRepository

class MessagesViewModel(application: Application):
    AndroidViewModel(application){
    private val _adaptador = MutableLiveData<MessageAdapter>().apply{
        value= MessageAdapter()
    }
    val adaptador:MutableLiveData<MessageAdapter> =_adaptador
    var repository= MessagesRepository.getInstance()

    val _latestInserted= MutableLiveData<Int>().apply{
        value= 0
    }
    val latestInserted:MutableLiveData<Int> =_latestInserted

    fun addMessage(msg: Message){
        // Afegim un missatge a traves de la instancia
        // del repositori
        repository.add(msg)
        _latestInserted.value=repository.getNumMessages()-1;
        _adaptador.value?.notifyItemInserted(latestInserted.value?:0)

        // I aci no fem el scroll. Aixo ho fara la vista.
    }
    fun deleteMessage(message: Message) {
        repository.deleteMessage(message.usuari, message.text)
    }

}