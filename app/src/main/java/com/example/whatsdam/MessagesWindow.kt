package com.example.whatsdam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMessagesWindowBinding
import com.example.whatsdam.Viewmodels.MessageAdapter
import com.example.whatsdam.model.MessageDataSet
import java.text.SimpleDateFormat
import java.util.Date

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
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Capturar variables anteriores
        val nick = intent.getStringExtra("nickname")
        val server = intent.getStringExtra("server")

        // textView del estado de conexión
        val connectionInfoTextView = binding.connectionInfoTextView
        connectionInfoTextView.text = "Connect a $server com $nick"


        sendMessage.setOnClickListener {
            val message = messageText.text.toString().trim()
            val hora = SimpleDateFormat("HH:mm").format(Date())

            if (message.isNotEmpty()) {
                // Add the message to the dataset
                if (nick != null) {
                    MessageDataSet.addMessage(nick, message, hora)
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
