package com.ieseljust.whatsdam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMessagesWindowBinding
import com.example.whatsdam.MessageDataSet
import com.example.whatsdam.MessageAdapter

class MessagesWindow : AppCompatActivity() {
    private lateinit var binding: ActivityMessagesWindowBinding
    private val adapter = MessageAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessagesWindowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sendMessage = binding.sendMessage
        val messageText = binding.MessageText
        val recyclerView = binding.recyclerView
        recyclerView.adapter = MessageAdapter()

        // Capturar variables anteriores
        val nick = intent.getStringExtra("nickname")
        val server = intent.getStringExtra("server")

        // textView del estado de conexión
        val connectionTextView = binding.connectionInfoTextView
        connectionTextView.text = "Connect a $server com $nick"

        sendMessage.setOnClickListener {
            val newMessage = messageText.text.toString()
            if (newMessage.isNotEmpty()) {
                MessageDataSet.addMessage(nick, newMessage)
                adapter.notifyDataSetChanged()
                messageText.text.clear()
            }
        }
    }
}
