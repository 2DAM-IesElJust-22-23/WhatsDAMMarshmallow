package com.ieseljust.whatsdam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ieseljust.whatsdam.databinding.ActivityMessagesWindowBinding
class MessagesWindow : AppCompatActivity() {
    private lateinit var binding: ActivityMessagesWindowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMessagesWindowBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(R.layout.activity_messages_window)

        val sendMessage= binding.sendMessage
        val messageText=binding.MessageText
        sendMessage.setOnClickListener{
            messageText.setText("")

        }

    }
}