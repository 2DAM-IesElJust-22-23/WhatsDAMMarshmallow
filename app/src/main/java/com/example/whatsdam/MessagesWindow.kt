package com.example.whatsdam

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whatsdamMarshmelow.databinding.ActivityMessagesWindowBinding
import java.util.Date

class MessagesWindow : AppCompatActivity() {
    private lateinit var binding: ActivityMessagesWindowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMessagesWindowBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.MensageRecyclerView.layoutManager= LinearLayoutManager(this)
        binding.MensageRecyclerView.setHasFixedSize(true)
        binding.MensageRecyclerView.adapter=AdaptadorMensage()

        val sendMessage = binding.sendMessage
        val messageText = binding.MessageText

        //Capturar variables anteriores
        val nick:String = intent.getStringExtra("nickname").toString()
        val server:String? = intent.getStringExtra("server")

        //textView del estado de conexión
        val connectionTextView =binding.connectionInfoTextView
        connectionTextView.text = "Connect a ${server.toString()} com ${nick.toString()}"

        sendMessage.setOnClickListener{
            val dateFormat = SimpleDateFormat("HH:mm")

            val horaActual = Date()

            val horaFormatada=dateFormat.format(horaActual)

            var mensage:Mensage= Mensage(nick,messageText.text.toString(),horaFormatada)

            Mensages.add(mensage)

            binding.MensageRecyclerView.adapter?.notifyItemInserted(Mensages.mensages.size-1)
        }
    }
}