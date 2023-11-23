package com.example.whatsdam.view.ui

import android.content.Intent
import android.net.InetAddresses.isNumericAddress
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.myapplication.communications.CommunicationManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.whatsdam.repositori.MessagesRepository

class MainActivity : AppCompatActivity() {
    val repository= MessagesRepository.getInstance()
    private lateinit var binding:ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nickText=binding.nickNameText
        val connBut=binding.buttonConnect
        val iptext=binding.serverAddressText

        connBut.setOnClickListener{
            val ipstr:String = iptext.text.toString()
            val nick=nickText.text.toString()

            if (nick!="" && isNumericAddress(ipstr)){
                val intent = Intent(baseContext, MessagesWindow::class.java)
                repository.username=nick
                repository.server=ipstr
                startActivity(intent)
            }
        }

    }
}