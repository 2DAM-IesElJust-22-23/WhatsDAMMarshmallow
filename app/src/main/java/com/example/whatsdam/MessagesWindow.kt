package com.example.whatsdam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMessagesWindowBinding

class MessagesWindow : AppCompatActivity() {
    private lateinit var binding: ActivityMessagesWindowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityMessagesWindowBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}