package com.example.whatsdam

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsdamMarshmelow.R

class MensageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val men=itemView.findViewById<TextView>(R.id.msg_text)
    val time=itemView.findViewById<TextView>(R.id.msg_me_timestamp)

    fun bind(mensage: Mensage){
        men.text=mensage.contengut
        time.text=mensage.hora
    }


}