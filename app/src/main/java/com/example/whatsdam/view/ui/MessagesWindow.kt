package com.example.whatsdam.view.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMessagesWindowBinding
import com.example.whatsdam.viewmodels.MessageAdapter
import com.example.whatsdam.viewmodels.MessageViewModel
import com.example.whatsdam.model.Message
import com.example.whatsdam.repositori.MessagesRepository
import java.text.SimpleDateFormat
import java.util.Date

class MessagesWindow : AppCompatActivity() {
    private lateinit var binding: ActivityMessagesWindowBinding
    private val adapter = MessageAdapter()

    // Referencia al repositori
    val repository= MessagesRepository.getInstance()

    // Adaptació a MVVM: Definim una instància del ViewModel com a lateinit
    private lateinit var viewModel: MessageViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessagesWindowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel =   ViewModelProvider(this)[MessageViewModel::class.java]

        val sendMessage = binding.sendMessage
        val messageText = binding.MessageText
        val recyclerView = binding.recyclerView

        recyclerView.adapter = MessageAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Capturar variables anteriores
        val nick = repository.username
        val server = repository.server

        binding.connectionInfoTextView.text="Connectat a ${server} com a ${nick}"


        viewModel.latestInserted.observe(this) {
            recyclerView.smoothScrollToPosition(it)
        }

        // Escoltem l'adaptador per al RecyclerView
        viewModel.adaptador.observe(this) {
            recyclerView.adapter = it

            // Fem scroll a l'ultim element, per carregar el final
            // de la conversa en la carrega de missatges inicial
            var latest=(recyclerView.adapter?.itemCount?:0)-1
            if (latest==-1) latest=0
            recyclerView.smoothScrollToPosition(latest)
        }

        sendMessage.setOnClickListener {
            val message = messageText.text.toString().trim()
            val hora = SimpleDateFormat("HH:mm").format(Date())

            if (message.isNotEmpty()) {
                // Add the message to the dataset
                if (nick != null) {
                    var msg=Message(nick,message)
                    viewModel.addMessage(msg)
                }

                // Notify the adapter that the dataset has changed
                adapter.notifyDataSetChanged()

                // Clear the messageText EditText
                messageText.text.clear()

                // Optionally, scroll the RecyclerView to the last item
                recyclerView.smoothScrollToPosition(adapter.itemCount - 1)
            }
        }


    }
}
