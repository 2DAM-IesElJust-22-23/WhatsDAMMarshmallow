package com.ieseljust.whatsdam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMessagesWindowBinding

class MessagesWindow : AppCompatActivity() {
    private lateinit var binding: ActivityMessagesWindowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMessagesWindowBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        val sendMessage = binding.sendMessage
        val messageText = binding.MessageText

        //Capturar variables anteriores
        val nick = intent.getStringExtra("nickname")
        val server = intent.getStringExtra("server")

        //textView del estado de conexión
        val connectionTextView =binding.connectionInfoTextView
        connectionTextView.text = "Connect a $server com $nick"

        sendMessage.setOnClickListener{

            messageText.text.clear()

        }
    }
}