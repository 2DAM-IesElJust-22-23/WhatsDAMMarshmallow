package com.example.whatsdam

import android.content.Context
import android.content.Intent
import android.net.InetAddresses.isNumericAddress
import android.net.InetAddresses.parseNumericAddress
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony.Mms.Intents
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
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
            var ipstr:String = iptext.text.toString()

            var nick=nickText.text.toString()
            Log.d("AAAAAA",ipstr)
            if (nick!="" && isNumericAddress(ipstr)){
                //var ip= parseNumericAddress(ipstr)
                val intent = Intent(baseContext,MessagesWindow::class.java)
                startActivity(intent)

            }
        }




    }
}