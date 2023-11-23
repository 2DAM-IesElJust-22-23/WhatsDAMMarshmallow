package com.example.whatsdam.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.whatsdam.model.Message
import com.example.whatsdam.repositori.MessagesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MessageViewModel : ViewModel() {
    // Referencia al repositorio
    private val repository = MessagesRepository.getInstance()

    // LiveData para el adaptador del RecyclerView
    private val _adapterLiveData = MutableLiveData<MessageAdapter>()
    val adapterLiveData: LiveData<MessageAdapter>
        get() = _adapterLiveData

    // LiveData para la posición del último elemento
    private val _lastInsertedPositionLiveData = MutableLiveData<Int>()

    val lastInsertedPositionLiveData: LiveData<Int>
        get() = _lastInsertedPositionLiveData

    // LiveData para la lista de mensajes
    val messageList: LiveData<ArrayList<Message>> by lazy {
        repository.messages
    }

    // Método para agregar un mensaje
    fun addMessage(msg: Message) {
        GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                repository.sendMessage(msg)
            }
        }
    }

    // Método para obtener el nombre de usuario
    fun getUserName(): String {
        return repository.username
    }

    // Método para obtener el servidor
    fun getServer(): String {
        return repository.server
    }
}
